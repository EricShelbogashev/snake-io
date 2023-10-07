package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import res.Font

@Composable
fun CancelButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier) {
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White, // Set your desired background color
                contentColor = Color.White // Set your desired text color
            ),
            content = {
                Text(
                    text = "Отмена",
                    style = Font.snakeIOTypography.button,
                    color = Color.Black // Set your desired text color
                )
            }
        )
    }
}