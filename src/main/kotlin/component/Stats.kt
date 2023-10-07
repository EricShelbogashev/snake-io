package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import res.Font.snakeIOTypography
import res.GameIcon

@Composable
fun Stats(
    modifier: Modifier = Modifier,
    fieldHeight: Int,
    fieldWidth: Int,
    foodStatic: Int,
    stateDelayMs: Int
) {
    val statItems = listOf(
        StatItem(GameIcon.HEIGHT, "Высота поля", fieldHeight.toString()),
        StatItem(GameIcon.WIDTH, "Ширина поля", fieldWidth.toString()),
        StatItem(GameIcon.FOOD, "Статическая еда", foodStatic.toString()),
        StatItem(GameIcon.DELAY, "Задержка состояния", "$stateDelayMs мс")
    )

    val contentPadding = 2.dp
    Column(modifier) {
        Text("Текущая игра", style = snakeIOTypography.h4)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(115.dp),
            contentPadding = PaddingValues(0.dp, 4.dp, 0.dp, 4.dp),
            content = {
                items(statItems) { item ->
                    StatChip(item.icon, item.label, item.value, Modifier.padding(contentPadding))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun StatChip(icon: GameIcon, label: String, value: String, modifier: Modifier) {
    Chip(
        onClick = { /* Do something! */ },
        border = BorderStroke(ChipDefaults.OutlinedBorderSize, Color.LightGray),
        shape = RoundedCornerShape(12.dp),
        colors = ChipDefaults.chipColors(backgroundColor = Color.White),
        leadingIcon = {
            Icon(
                painter = icon.painter(),
                contentDescription = label
            )
        },
        modifier = modifier.sizeIn(minWidth = 80.dp, minHeight = 48.dp)
    ) {
        Column {
            Text(label, style = snakeIOTypography.h6, softWrap = true)
            Text(value, style = snakeIOTypography.h5, softWrap = true)
        }
    }
}

data class StatItem(val icon: GameIcon, val label: String, val value: String)
