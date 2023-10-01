package component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.api.v1.dto.Game
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
