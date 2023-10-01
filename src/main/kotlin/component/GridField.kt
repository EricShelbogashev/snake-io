package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.GameConfig
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun GridField(
    modifier: Modifier = Modifier,
    gameConfig: GameConfig,
    cells: MutableMap<Int, Color>
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(gameConfig.width),
    ) {
        items(gameConfig.height * gameConfig.width) { index ->
            val random = Random(index).nextInt(220..230)
            val color = cells[index] ?: Color(
                random,
                random,
                random
            )
            Card(
                backgroundColor = color,
                modifier = Modifier.width(10.dp).aspectRatio(1f)
            ) {
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGridField() {
}