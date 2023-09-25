package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import res.GameIcon

@Composable
fun Chip(
    icon: GameIcon,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier,
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        val padding = 4.dp
        Row(Modifier.padding(end = padding, start = padding, top = padding / 2, bottom = padding / 2)) {
            Icon(
                modifier = Modifier.height(42.dp).width(42.dp),
                painter = icon.painter(),
                contentDescription = "game app logo"
            )
            Text(
                modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterVertically),
                text = text,
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Preview
@Composable
private fun PreviewChip() {
    Chip(GameIcon.FOOD, "2")
}