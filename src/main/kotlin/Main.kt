import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import config.ClientSettings
import model.GameController
import model.api.v1.dto.Direction
import model.state.State
import model.state.impl.LobbyState
import model.state.impl.MasterMatchState
import model.state.impl.MatchState
import view.GameView
import view.LobbyView
import java.net.DatagramSocket
import java.net.MulticastSocket
import java.net.NetworkInterface

fun main() = application {
    val state: MutableState<State?> = remember { mutableStateOf(null) }
    val gameController = remember { mutableStateOf<GameController?>(null) }
    val client = createSnakeGameClient(state, gameController)

    Window(
        onCloseRequest = ::exitApplication,
        title = "SnakeGame",
        state = rememberWindowState(width = 1200.dp, height = 680.dp),
        onKeyEvent = { handleKeyEvent(gameController, it) }
    ) {
        renderView(state.value, client, gameController)
    }
}

private fun createSnakeGameClient(
    state: MutableState<State?>,
    gameController: MutableState<GameController?>
): Client {
    return Client(
        onStateChanged = {
            handleStateChanged(it, state, gameController)
        },
        settings = ClientSettings(
            multicastReceiveSocket = MulticastSocket(ClientSettings.gameGroupAddress()),
            generalSocket = DatagramSocket(),
            networkInterface = NetworkInterface.getByName("wlan0")
        )
    )
}

private fun handleStateChanged(
    newState: State,
    state: MutableState<State?>,
    gameController: MutableState<GameController?>
) {
    if (newState is LobbyState) {
        gameController.value = null
    }
    state.value = newState
}

private fun handleKeyEvent(gameController: MutableState<GameController?>, keyEvent: KeyEvent): Boolean {
    if (gameController.value == null) return false

    try {
        gameController.value!!.move(
            when (keyEvent.key) {
                Key.W, Key.DirectionUp -> Direction.UP
                Key.D, Key.DirectionRight -> Direction.RIGHT
                Key.S, Key.DirectionDown -> Direction.DOWN
                Key.A, Key.DirectionLeft -> Direction.LEFT
                else -> return false
            }
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return true
}

@Composable
private fun renderView(state: State?, client: Client, gameController: MutableState<GameController?>) {
    if (state != null) {
        when (state) {
            is LobbyState -> LobbyView(client.lobbyController())
            is MasterMatchState -> {
                GameView(client.gameController())
                gameController.value = client.gameController()
            }

            is MatchState -> {
                GameView(client.gameController())
                gameController.value = client.gameController()
            }
        }
    }
}
