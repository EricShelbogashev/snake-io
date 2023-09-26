package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import res.Font

@Composable
fun GameLeaveButton(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth().size(56.dp),
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White, // Set your desired background color
                contentColor = Color.White // Set your desired text color
            ),
            content = {
                Text(
                    text = "Выйти из игры",
                    style = Font.snakeIOTypography.button,
                    color = Color.Black // Set your desired text color
                )
            }
        )
    }
}