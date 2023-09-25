package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import res.GameIcon

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Icon(
            modifier = Modifier.height(42.dp).width(42.dp),
            painter = GameIcon.LOGO.painter(),
            contentDescription = "game app logo"
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Snake.IO",
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview
@Composable
private fun PreviewLogo() {
    Logo(Modifier.padding(8.dp))
}