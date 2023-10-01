package view

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
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun GameView(gameController: GameController) {
    val config = gameController.config()
    val modifier = Modifier.fillMaxSize()
    val playersState = remember { mutableStateOf(arrayOf<Player>()) }

    val fadedColorsEnable = remember { mutableStateOf(true) }
    val cells = remember { mutableStateOf(mutableMapOf<Int, Color>()) }
    gameController.setOnGameStateChangeListener { state: GameState ->
        val cellsTmp = mutableMapOf<Int, Color>()
        state.snakes.forEach { snake ->
            snake.points.forEach { coords ->
                cellsTmp[config.width * coords.y + coords.x] = ColorResolver.resolveSnake(fadedColorsEnable.value && gameController.currentPlayer().id != snake.playerId, snake.playerId)
            }
        }
        state.food.forEach { food ->
            cellsTmp[config.width * food.y + food.x] = ColorResolver.resolveFood(food.x, food.y)
        }
        playersState.value = state.players
        cells.value.clear()
        cells.value = cellsTmp
    }

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
                    generalComponentsModifier, gameController.currentPlayer().name, players = playersState.value, expandedInfoEnable.value
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
}

object ColorResolver {
    private val colors = arrayOf(
        Color.Black,
        Color.Blue,
        Color.Red,
        Color.Cyan,
        Color.Green,
        Color.Magenta
    )

    fun resolveFood(x: Int, y: Int): Color {
        val random = Random(x * y)
        return Color(
            random.nextInt(50..250),
            random.nextInt(50..250),
            random.nextInt(50..250)
        )
    }

    fun resolveSnake(faded: Boolean, id: Int): Color {
        val colorBase = colors[id.absoluteValue % colors.size]
        val offset = ((id.absoluteValue / colors.size) % 10) * 10 * if (id % 2 == 0) -1 else 1
        var red = colorBase.red + offset
        var green = colorBase.green + offset
        var blue = colorBase.blue + offset

        if (faded && red < 190) {
            red = 180 + (red % 50)
        }
        if (faded && green < 190) {
            green = 180 + (green % 50)
        }
        if (faded && blue < 190) {
            blue = 180 + (blue % 50)
        }

        return Color(red, green, blue)
    }
}