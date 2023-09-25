package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import res.GameIcon
import java.net.InetSocketAddress
import java.net.SocketAddress

@Composable
fun GameItem(
    username: String,
    address: InetSocketAddress,
    width: Int,
    height: Int,
    foodStatic: Int,
    alive: Int,
    onView: (address: SocketAddress) -> Unit,
    onJoin: (address: SocketAddress) -> Unit,
    modifier: Modifier = Modifier
) {
    val usernameWeight = .3f
    val addressWeight = .3f
    val sizeWeight = .1f
    val formulaWeight = .1f
    val buttonsWeight = .2f
    Card(modifier = modifier) {
        val textStyle = MaterialTheme.typography.body1
        val padding = 4.dp
        Row {
            Text(
                text = username,
                modifier = Modifier.weight(usernameWeight).align(Alignment.CenterVertically).padding(padding),
                style = textStyle
            )
            Text(
                text = "${address.hostName}:${address.port}",
                modifier = Modifier.weight(addressWeight).align(Alignment.CenterVertically).padding(padding),
                style = textStyle
            )
            Text(
                text = "${width}x${height}",
                modifier = Modifier.weight(sizeWeight).align(Alignment.CenterVertically).padding(padding),
                style = textStyle
            )
            Text(
                text = "${alive}x + $foodStatic",
                modifier = Modifier.weight(formulaWeight).align(Alignment.CenterVertically).padding(padding),
                style = textStyle
            )
            Box(Modifier.weight(buttonsWeight).padding(padding)) {
                Row(Modifier.align(Alignment.CenterEnd)) {
                    IconButton(
                        onClick = { onView(address) },
                        modifier = Modifier.align(Alignment.CenterVertically),
                    ) {
                        Icon(
                            painter = GameIcon.JOIN_GAME.painter(),
                            contentDescription = "join the game button"
                        )
                    }
                    IconButton(
                        onClick = { onJoin(address) },
                        modifier = Modifier.align(Alignment.CenterVertically),
                    ) {
                        Icon(
                            painter = GameIcon.VIEW_GAME.painter(),
                            contentDescription = "watch the game button"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewStats() {
    GameItem("Виктор", InetSocketAddress(2233), 30, 20, 3, 4, { println("on view") }, { println("on join") })
}