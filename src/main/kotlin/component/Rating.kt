package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.api.v1.dto.NodeRole
import model.api.v1.dto.Player
import res.Font.snakeIOTypography

@Composable
fun Rating(
    modifier: Modifier = Modifier,
    selfName: String,
    players: Array<Player>,
    expandedInfoEnable: Boolean
) {
    Column(modifier) {
        Text("Рейтинг", style = snakeIOTypography.h4)
        Card(
            backgroundColor = Color(240, 240, 240),
            elevation = 0.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(4.dp),
                content = {
                    items(players) { player ->
                        PlayerRatingItem(player, selfName, expandedInfoEnable)
                    }
                }
            )
        }
    }
}

@Composable
fun PlayerRatingItem(player: Player, selfName: String, expandedInfoEnable: Boolean) {
    val isMe = player.name == selfName
    val backgroundColor = if (isMe) {
        Color.LightGray
    } else {
        Color.Transparent
    }
    val textStyle = if (player.role == NodeRole.VIEWER) {
        snakeIOTypography.h6
    } else {
        snakeIOTypography.h5
    }
    Card(
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            Modifier
                .padding(4.dp)
        ) {
            Box(Modifier.fillMaxWidth()) {
                Text(player.name, style = textStyle, modifier = Modifier.align(Alignment.CenterStart))
                if (player.role != NodeRole.VIEWER) {
                    Text(
                        player.score.toString(),
                        style = textStyle,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
            if (expandedInfoEnable) {
                PlayerRatingItemTextExpandable(player)
            }
        }
    }
}

@Composable
fun PlayerRatingItemTextExpandable(player: Player) {
    val address = try {
        player.address
    } catch (e: Exception) {
        null
    }
    if (address != null) {
        Text("${player.ip}:${player.port}")
    }
    Text("NodeRole: ${player.role}", style = snakeIOTypography.caption)
    Text("PlayerType: ${player.type}", style = snakeIOTypography.caption)
}
