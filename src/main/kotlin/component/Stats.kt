package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.GameConfig
import res.GameIcon

@Composable
fun Stats(
    gameConfig: GameConfig,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text("Текущая игра", style = MaterialTheme.typography.h4)
        Row {
            Chip(GameIcon.HEIGHT, gameConfig.height.toString())
            Chip(GameIcon.WIDTH, gameConfig.width.toString())
            Chip(GameIcon.FOOD, "${gameConfig.foodStatic}")
        }
    }
}

@Preview
@Composable
private fun PreviewStats() {
    Stats(GameConfig("random name", "Petya", 30, 29, 3, 4040))
}