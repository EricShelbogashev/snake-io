import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import res.Font

enum class GameOption {
    OPPONENTS_FADED_COLORS,
    EXPANDED_INFORMATION_ABOUT_PARTICIPANTS,
}

@Composable
fun GameSettings(
    modifier: Modifier = Modifier,
    action: (selected: GameOption, isChecked: Boolean) -> Unit
) {
    Column(modifier) {
        Text("Настройки игры", style = Font.snakeIOTypography.h4)
        OptionRow(
            label = "Блеклые цвета оппонентов",
            isChecked = true,
            onCheckedChange = { checked ->
                action(GameOption.OPPONENTS_FADED_COLORS, checked)
            }
        )
        OptionRow(
            label = "Расширенная информация об участниках",
            isChecked = false,
            onCheckedChange = { checked ->
                action(GameOption.EXPANDED_INFORMATION_ABOUT_PARTICIPANTS, checked)
            }
        )
    }
}

@Composable
fun OptionRow(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    var checkedState by remember { mutableStateOf(isChecked) }

    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(label, modifier = Modifier.weight(1f))
        Switch(
            checked = checkedState,
            onCheckedChange = { checked ->
                checkedState = checked
                onCheckedChange(checked)
            },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
