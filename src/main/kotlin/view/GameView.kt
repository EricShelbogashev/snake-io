package view

import GameOption
import GameSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import component.*
import model.GameController
import model.api.v1.dto.GameState
import model.api.v1.dto.Player

@Composable
fun GameView(gameController: GameController) {
    val config = gameController.config()
    val modifier = Modifier.fillMaxSize()
    val playersState = remember { mutableStateOf(arrayOf<Player>()) }

    val fadedColorsEnable = remember { mutableStateOf(true) }
    val cells = remember { mutableStateOf(mutableMapOf<Int, Color>()) }

    Row(modifier) {
        // Padding.
        val generalComponentPadding = 6.dp

        // General properties.
        val generalColumnModifier = Modifier.fillMaxHeight()
        val generalComponentsModifier = Modifier.padding(generalComponentPadding)

        // Column with game information.
        val gameInfoColumnModifier = generalColumnModifier.weight(.2f)
        val expandedInfoEnable = remember { mutableStateOf(false) }

        Column(modifier = gameInfoColumnModifier) {
            Logo(generalComponentsModifier)

            Column(
                Modifier.fillMaxHeight(0.9f), verticalArrangement = Arrangement.Center
            ) {
                Rating(
                    generalComponentsModifier,
                    gameController.currentPlayer().name,
                    players = playersState.value,
                    expandedInfoEnable.value
                )

                Stats(
                    generalComponentsModifier, config.height, config.width, config.foodStatic, config.stateDelayMs
                )
            }
        }

        // Column with game field.
        val gameFieldColumnModifier = generalColumnModifier.weight(.6f).background(Color(240, 240, 240))

        Column(
            modifier = gameFieldColumnModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GridField(modifier = generalComponentsModifier, config, cells.value)
        }

        // Column with game settings.
        val gameSettingsColumnModifier = generalColumnModifier.weight(.2f)
        Column(modifier = gameSettingsColumnModifier) {
            GameLeaveButton(
                generalComponentsModifier
            ) { gameController.leaveGame() }
            Column(
                Modifier.fillMaxHeight(0.9f), verticalArrangement = Arrangement.Center
            ) {
                GameSettings(modifier = generalComponentsModifier,
                    action = { selected, isChecked ->
                        when (selected) {
                            GameOption.EXPANDED_INFORMATION_ABOUT_PARTICIPANTS -> {
                                expandedInfoEnable.value = isChecked
                            }

                            GameOption.OPPONENTS_FADED_COLORS -> {
                                fadedColorsEnable.value = isChecked
                            }
                        }
                    })
            }
        }
    }

    remember {
        gameController.setOnGameStateChangeListener { state: GameState ->
            val cellsTmp = mutableMapOf<Int, Color>()
            state.snakes.forEach { snake ->
                snake.points.forEach { coords ->
                    cellsTmp[config.width * coords.y + coords.x] = ColorResolver.resolveSnake(
                        fadedColorsEnable.value && gameController.currentPlayer().id != snake.playerId,
                        snake.playerId
                    )
                }
            }
            state.food.forEach { food ->
                cellsTmp[config.width * food.y + food.x] = ColorResolver.resolveFood(food.x, food.y)
            }
            playersState.value = state.players
            cells.value.clear()
            cells.value = cellsTmp
        }
    }
}

object ColorResolver {
    fun resolveFood(x: Int, y: Int): Color {
        return KELLY_COLORS_FOOD[x * y % KELLY_COLORS_FOOD.size]
    }

    fun resolveSnake(faded: Boolean, id: Int): Color {
        var color = KELLY_COLORS_SNAKES[id % KELLY_COLORS_SNAKES.size]
        val maxVal = Color.White.green
        if (faded) {
            color = Color(
                (color.red + maxVal) / 2,
                (color.green + maxVal) / 2,
                (color.blue + maxVal) / 2,
                1f,
            )
        }
        return color
    }

    private val KELLY_COLORS_SNAKES = arrayOf(
        Colors.Red,
        Colors.Pink,
        Colors.Purple,
        Colors.Indigo,
        Colors.Blue,
        Colors.Cyan,
        Colors.Teal,
        Colors.Green,
        Colors.Lime,
        Colors.Brown,
        Colors.Grey
    )

    private val KELLY_COLORS_FOOD = arrayOf(
        Colors.Amber,
        Colors.Orange,
        Colors.Yellow,
    )
}