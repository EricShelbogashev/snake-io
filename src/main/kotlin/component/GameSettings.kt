package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

enum class GameOption {
    OPPONENTS_FADED_COLORS,
    EXPANDED_INFORMATION_ABOUT_PARTICIPANTS,
}

@Composable
fun GameSettings(
    action: (GameOption, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text("Текущая игра", style = MaterialTheme.typography.h4)
        Column {
            val opponentsFadedColorsCheckedState = remember { mutableStateOf(true) }
            Row {
                Text("Блеклые цвета для соперников")
                Switch(opponentsFadedColorsCheckedState.value, {checked ->
                    opponentsFadedColorsCheckedState.value = checked
                    action(GameOption.OPPONENTS_FADED_COLORS, checked)
                    checked != checked
                })
            }
            val expandedInformationAboutParticipantsCheckedState = remember { mutableStateOf(false) }
            Row {
                Text("Расширенная информация об участниках")
                Switch(expandedInformationAboutParticipantsCheckedState.value, {checked ->
                    expandedInformationAboutParticipantsCheckedState.value = checked
                    action(GameOption.EXPANDED_INFORMATION_ABOUT_PARTICIPANTS, checked)
                })
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGameSettings() {
    GameSettings({ option: GameOption, enable: Boolean ->
        println("${option.name} -> $enable")
    })
}