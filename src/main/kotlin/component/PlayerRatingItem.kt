package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PlayerRatingItem(
    username: String,
    score: Int,
    selected: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Card(
            elevation = 0.dp, backgroundColor = if (selected) {
                Color.LightGray
            } else {
                Color.Transparent
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            val style = MaterialTheme.typography.body1
            val padding = 6.dp
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = username,
                    modifier = Modifier.align(Alignment.CenterStart).padding(padding),
                    style = style
                )
                Text(
                    text = score.toString(),
                    modifier = Modifier.align(Alignment.CenterEnd).padding(padding),
                    style = style
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPlayerRatingItem() {
    Column {
        PlayerRatingItem("petya", 30)
        PlayerRatingItem("tanya", 10, true)
    }
}