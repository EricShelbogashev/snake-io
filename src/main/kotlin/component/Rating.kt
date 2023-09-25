package component

import androidx.compose.foundation.background
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
import api.v1.dto.Player
import res.Font.snakeIOTypography

@Composable
fun Rating(
    modifier: Modifier = Modifier,
    players: Array<Player>
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
                        PlayerRatingItem(player)
                    }
                }
            )
        }
    }
}

@Composable
fun PlayerRatingItem(player: Player) {
    val isDasha = player.name == "dasha"
    if (isDasha) {
        Card(
            backgroundColor = Color.LightGray,
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(player.name, style = snakeIOTypography.h5, modifier = Modifier.align(Alignment.CenterStart))
                Text(player.score.toString(), style = snakeIOTypography.h5, modifier = Modifier.align(Alignment.CenterEnd))
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
