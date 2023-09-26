package component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import api.v1.dto.Game
import res.Font
import res.GameIcon
import java.net.InetSocketAddress
import java.net.SocketAddress

@Composable
fun GameItemBlock(
    modifier: Modifier = Modifier,
    address: InetSocketAddress,
    games: Array<Game>,
    onView: (address: SocketAddress) -> Unit,
    onJoin: (address: SocketAddress) -> Unit,
    last: Boolean
) {
    Card(
        modifier = modifier
            .padding(start = 6.dp, end = 17.dp, top = 6.dp, bottom = if (last) 6.dp else 0.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column {
            games.forEach { game: Game ->
                GameItem(
                    username = game.config.playerName,
                    address = address,
                    width = game.config.width,
                    height = game.config.height,
                    foodStatic = game.config.foodStatic,
                    alive = game.players.size,
                    canJoin = game.canJoin,
                    onView = onView,
                    onJoin = onJoin
                )
            }
        }
    }
}
