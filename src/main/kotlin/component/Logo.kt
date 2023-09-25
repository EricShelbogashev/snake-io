package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import res.Font.snakeIOTypography
import res.GameIcon

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(42.dp),
            painter = GameIcon.LOGO.painter(),
            contentDescription = "game app logo"
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Snake.IO",
            style = snakeIOTypography.h3
        )
    }
}

@Preview
@Composable
private fun PreviewLogo() {
    Logo(Modifier.padding(8.dp))
}