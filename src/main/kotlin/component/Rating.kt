package component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import api.v1.dto.NodeRole
import api.v1.dto.Player
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
    if (isMe) {
        Card(
            backgroundColor = Color.LightGray,
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                Modifier
                    .padding(4.dp)
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Text(player.name, style = snakeIOTypography.h5, modifier = Modifier.align(Alignment.CenterStart))
                    Text(player.score.toString(), style = snakeIOTypography.h5, modifier = Modifier.align(Alignment.CenterEnd))
                }
                if (expandedInfoEnable) {
                    if (player.role != NodeRole.MASTER) {
                        Text("${player.ip}:${player.port}")
                    }
                    Text("NodeRole: ${player.role}", style = snakeIOTypography.caption)
                    Text("PlayerType: ${player.type}", style = snakeIOTypography.caption)
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(player.name, style = snakeIOTypography.body2, modifier = Modifier.align(Alignment.CenterStart))
            Text(player.score.toString(), style = snakeIOTypography.body2, modifier = Modifier.align(Alignment.CenterEnd))
        }
    }
}
