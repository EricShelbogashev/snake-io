import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.input.key.Key
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
import view.GameView
import view.LobbyView
import java.net.DatagramSocket
import java.net.MulticastSocket
import java.net.NetworkInterface

fun main() = application {
    val state: MutableState<State?> = remember { mutableStateOf(null) }
    val gameController = remember { mutableStateOf<GameController?>(null) }
    val client = Client(
        onStateChanged = {
            if (it is LobbyState) {
                gameController.value = null
            }
            state.value = it
        },
        settings = ClientSettings(
            multicastReceiveSocket = MulticastSocket(ClientSettings.gameGroupAddress()),
            generalSocket = DatagramSocket(),
            networkInterface = NetworkInterface.getByName("wlan0")
        )
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "SnakeGame",
        state = rememberWindowState(width = 1200.dp, height = 680.dp),
        onKeyEvent = {
            if (gameController.value == null) return@Window false

            try {
                gameController.value!!.move(
                    when (it.key) {
                        Key.W, Key.DirectionUp -> Direction.UP
                        Key.D, Key.DirectionRight -> Direction.RIGHT
                        Key.S, Key.DirectionDown -> Direction.DOWN
                        Key.A, Key.DirectionLeft -> Direction.LEFT
                        else -> return@Window false
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            true
        }
    ) {
        if (state.value != null) {
            if (state.value is LobbyState) {
                LobbyView(client.lobbyController())
            } else {
                GameView(client.gameController())
                gameController.value = client.gameController()
            }
        }
    }
}


//fun main() = application {
//    val applicationState = remember { MyApplicationState() }
//
//    for (window in applicationState.windows) {
//        key(window) {
//            MyWindow(window)
//        }
//    }
//}
//
//@Composable
//private fun MyWindow(
//    state: MyWindowState
//) = Window(onCloseRequest = state::close, title = state.title) {
//    MenuBar {
//        Menu("File") {
//            Item("New window", onClick = state.openNewWindow)
//            Item("Exit", onClick = state.exit)
//        }
//    }
//}
//
//private class MyApplicationState {
//    val windows = mutableStateListOf<MyWindowState>()
//
//    init {
//        windows += MyWindowState("Initial window")
//    }
//
//    fun openNewWindow() {
//        windows += MyWindowState("Window ${windows.size}")
//    }
//
//    fun exit() {
//        windows.clear()
//    }
//
//    private fun MyWindowState(
//        title: String
//    ) = MyWindowState(
//        title,
//        openNewWindow = ::openNewWindow,
//        exit = ::exit,
//        windows::remove
//    )
//}
//
//private class MyWindowState(
//    val title: String,
//    val openNewWindow: () -> Unit,
//    val exit: () -> Unit,
//    private val close: (MyWindowState) -> Unit
//) {
//    fun close() = close(this)
//}