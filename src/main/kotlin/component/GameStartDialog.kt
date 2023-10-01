package component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.api.v1.dto.GameConfig
import res.Font
import java.util.*

@Composable
fun GameStartDialog(
    openDialog: MutableState<Boolean>,
    newGame: (gameName: String, playerName: String, config: GameConfig) -> Unit
) {
    // TODO: обработка случая, когда указано лишь одно из ширины и высоты, а остальное по-умолчанию.
    // TODO: обработка случая, когда количество постоянной еды
    //       указано больше, чем возможно, из-за изменения высоты/ширины.
    // TODO: рефактор.
    val defaultGameName = "Game ${UUID.randomUUID().toString().subSequence(1..7)}"
    val defaultPlayerName = "Player ${UUID.randomUUID().toString().subSequence(1..3)}"

    var gameNameText by remember { mutableStateOf("") }
    var playerNameText by remember { mutableStateOf("") }
    var heightText by remember { mutableStateOf("") }
    var widthText by remember { mutableStateOf("") }
    var foodStaticText by remember { mutableStateOf("") }
    var delayText by remember { mutableStateOf("") }

    var gameNameError by rememberSaveable { mutableStateOf(false) }
    var playerNameError by rememberSaveable { mutableStateOf(false) }
    var heightError by rememberSaveable { mutableStateOf(false) }
    var widthError by rememberSaveable { mutableStateOf(false) }
    var foodStaticError by rememberSaveable { mutableStateOf(false) }
    var delayError by rememberSaveable { mutableStateOf(false) }

    AlertDialog(onDismissRequest = {
        openDialog.value = false
    },
        shape = RoundedCornerShape(12.dp),
        title = {
            Text(text = "Новая игра", style = Font.snakeIOTypography.h4)
        }, text = {
            Column {
                val padding = 6.dp
                val itemModifier = Modifier.padding(padding)
//                GameConfig(gameName = , playerName = , width = , height = , foodStatic = , stateDelayMs = )
                Row {
                    OutlinedTextField(
                        label = {
                            Text(
                                if (!gameNameError) {
                                    "Название игры"
                                } else {
                                    "Не меньше 3 символов"
                                }
                            )
                        },
                        value = gameNameText,
                        onValueChange = { it: String ->
                            gameNameError = it.length in 1..3
                            gameNameText = it
                        },
                        modifier = itemModifier,
                        placeholder = { Text(defaultGameName, style = Font.snakeIOTypography.overline) },
                        textStyle = Font.snakeIOTypography.body1,
                        isError = gameNameError
                    )
                    OutlinedTextField(label = {
                        Text(
                            if (!playerNameError) {
                                "Имя игрока"
                            } else {
                                "Не меньше 3 символов"
                            }
                        )
                    }, value = playerNameText, onValueChange = {
                        playerNameError = it.length in 1..3
                        playerNameText = it
                    }, modifier = itemModifier, placeholder = {
                        Text(
                            text = defaultPlayerName, style = Font.snakeIOTypography.caption
                        )
                    }, isError = playerNameError, textStyle = Font.snakeIOTypography.body1
                    )
                }
                Row {
                    OutlinedTextField(label = {
                        Text(
                            if (!heightError) {
                                "Высота поля"
                            } else {
                                "От 10 до 100"
                            }
                        )
                    }, value = heightText, onValueChange = {
                        heightError = try {
                            val v = it.trim().toInt()
                            v !in 10..100
                        } catch (e: Exception) {
                            true
                        }
                        heightText = it
                    }, modifier = itemModifier, placeholder = {
                        Text(
                            text = 30.toString(), style = Font.snakeIOTypography.overline
                        )
                    }, isError = heightError, textStyle = Font.snakeIOTypography.body1
                    )
                    OutlinedTextField(label = {
                        Text(
                            if (!widthError) {
                                "Ширина поля"
                            } else {
                                "От 10 до 100"
                            }
                        )
                    }, isError = widthError, value = widthText, onValueChange = {
                        widthError = try {
                            val v = it.trim().toInt()
                            v !in 10..100
                        } catch (e: Exception) {
                            true
                        }
                        widthText = it
                    }, placeholder = {
                        Text(
                            text = 40.toString(), style = Font.snakeIOTypography.caption
                        )
                    }, modifier = itemModifier, textStyle = Font.snakeIOTypography.body1
                    )
                }
                Row {
                    OutlinedTextField(label = {
                        Text(
                            if (!foodStaticError) {
                                "Количество постоянной еды"
                            } else {
                                "От 1 до ${
                                    try {
                                        heightText.toInt() * widthText.toInt()
                                    } catch (e: Exception) {
                                        "98"
                                    }
                                }"
                            }
                        )
                    }, isError = foodStaticError, value = foodStaticText, onValueChange = {
                        foodStaticError = try {
                            val v = it.trim().toInt()
                            v !in 1..try {
                                heightText.toInt() * widthText.toInt()
                            } catch (e: Exception) {
                                98
                            }
                        } catch (e: Exception) {
                            true
                        }
                        foodStaticText = it
                    }, modifier = itemModifier, placeholder = {
                        Text(
                            text = 1.toString(),
                            style = Font.snakeIOTypography.caption
                        )
                    }, textStyle = Font.snakeIOTypography.body1
                    )
                    OutlinedTextField(label = {
                        Text(
                            if (!delayError) {
                                "Задержка шага (мс)"
                            } else {
                                "От 200 до 5000"
                            }
                        )
                    }, placeholder = {
                        Text(
                            text = 500.toString(),
                            style = Font.snakeIOTypography.caption
                        )
                    }, isError = delayError, value = delayText, onValueChange = {
                        delayError = try {
                            val v = it.trim().toInt()
                            v !in 200..5000
                        } catch (e: Exception) {
                            true
                        }
                        delayText = it
                    }, modifier = itemModifier, textStyle = Font.snakeIOTypography.body1
                    )
                }
            }
        }, dismissButton = {
            CancelButton {
                openDialog.value = false
            }
        }, confirmButton = {
            GameCreateButton {
                if (gameNameText.isEmpty()) {
                    gameNameText = defaultGameName
                }
                if (playerNameText.isEmpty()) {
                    playerNameText = defaultPlayerName
                }
                val width = if (widthText.isEmpty()) {
                    40
                } else {
                    widthText.trim().toInt()
                }
                val height = if (heightText.isEmpty()) {
                    30
                } else {
                    heightText.trim().toInt()
                }
                val foodStatic = if (foodStaticText.isEmpty()) {
                    1
                } else {
                    foodStaticText.trim().toInt()
                }
                val delay = if (delayText.isEmpty()) {
                    500
                } else {
                    delayText.trim().toInt()
                }

                newGame(
                    gameNameText,
                    playerNameText,
                    GameConfig(width, height, foodStatic, delay)
                )
            }
        })
}