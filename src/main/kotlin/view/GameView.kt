package view

import Colors
import GameOption
import GameSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import component.*
import kotlinx.coroutines.delay
import model.GameController
import model.api.v1.dto.GameConfig
import model.api.v1.dto.GameState
import model.api.v1.dto.Player

@Composable
fun GameView(gameController: GameController) {
    val config = gameController.config()
    val modifier = Modifier.fillMaxSize()

    val playersState = remember { mutableStateOf(arrayOf<Player>()) }
    val fadedColorsEnable = remember { mutableStateOf(true) }
    val cells = remember { mutableStateOf(mutableMapOf<Int, Color>()) }
    val expandedInfoEnable = remember { mutableStateOf(false) }
    var isComponentLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000)
        gameController.currentPlayer().name
        isComponentLoaded = true
    }

    Row(modifier) {
        val generalComponentPadding = 6.dp
        val generalColumnModifier = Modifier.fillMaxHeight()
        val generalComponentsModifier = Modifier.padding(generalComponentPadding)

        if (isComponentLoaded) {
            Column(modifier = generalColumnModifier.weight(.2f)) {
                GameInfoSection(gameController, config, generalComponentsModifier, playersState, expandedInfoEnable)
            }

            val gameFieldColumnModifier = generalColumnModifier.weight(.6f).background(Color(240, 240, 240))
            Column(
                modifier = gameFieldColumnModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GridField(modifier = generalComponentsModifier, config, cells.value)
            }

            val gameSettingsColumnModifier = generalColumnModifier.weight(.2f)
            Column(modifier = gameSettingsColumnModifier) {
                GameSettingsSection(generalComponentsModifier, fadedColorsEnable, expandedInfoEnable, gameController)
            }
        } else {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    Modifier.size(150.dp),
                    strokeWidth = 20.dp,
                    strokeCap = StrokeCap.Round,
                    color = Color(225, 225, 225)
                )
            }
        }
    }

    observeGameState(gameController, config, playersState, fadedColorsEnable, cells)
}

@Composable
private fun GameInfoSection(
    gameController: GameController,
    config: GameConfig,
    modifier: Modifier,
    playersState: MutableState<Array<Player>>,
    expandedInfoEnable: MutableState<Boolean>
) {
    Column(modifier) {
        Logo(modifier)
        Column(
            Modifier.fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.Center
        ) {
            Rating(modifier, gameController.currentPlayer().name, playersState.value, expandedInfoEnable.value)
            Stats(modifier, config.height, config.width, config.foodStatic, config.stateDelayMs)
        }
    }
}

@Composable
private fun GameSettingsSection(
    modifier: Modifier,
    fadedColorsEnable: MutableState<Boolean>,
    expandedInfoEnable: MutableState<Boolean>,
    gameController: GameController
) {
    GameLeaveButton(modifier) { gameController.leaveGame() }
    Column(
        Modifier.fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Center
    ) {
        GameSettings(modifier = modifier) { selected, isChecked ->
            when (selected) {
                GameOption.EXPANDED_INFORMATION_ABOUT_PARTICIPANTS -> {
                    expandedInfoEnable.value = isChecked
                }

                GameOption.OPPONENTS_FADED_COLORS -> {
                    fadedColorsEnable.value = isChecked
                }
            }
        }
    }
}

@Composable
private fun observeGameState(
    gameController: GameController,
    config: GameConfig,
    playersState: MutableState<Array<Player>>,
    fadedColorsEnable: MutableState<Boolean>,
    cells: MutableState<MutableMap<Int, Color>>
) {
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