package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import res.Font

@Composable
fun GameStartButton(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier) {
        Button(
            modifier = Modifier.fillMaxWidth().size(56.dp),
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            content = {
                Text(
                    text = "Начать игру",
                    style = Font.snakeIOTypography.button
                )
            })
    }
}