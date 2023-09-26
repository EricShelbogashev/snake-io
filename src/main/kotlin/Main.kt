import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import model.GameConfig
import view.GameView
import view.LobbyView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SnakeGame",
        state = rememberWindowState(width = 1200.dp, height = 680.dp)
    ) {
        GameView(
            GameConfig("Some game", "Petya", 33, 22, 4, 300)
        )
//        LobbyView()
    }
}