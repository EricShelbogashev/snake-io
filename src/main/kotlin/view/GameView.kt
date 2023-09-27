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
import api.v1.dto.GameState
import api.v1.dto.Player
import component.*
import controller.GameController
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun GameView(gameController: GameController) {
    val config = gameController.config()
    val modifier = Modifier.fillMaxSize()
    val playersState = remember { mutableStateOf(arrayOf<Player>()) }


    val cells = remember { mutableStateOf(mutableMapOf<Int, Color>()) }
    gameController.setOnGameStateChangeListener { state: GameState ->
        val cellsTmp = mutableMapOf<Int, Color>()
        state.snakes.forEach { snake ->
            snake.points.forEach { coords ->
                cellsTmp[config.width * coords.y + coords.x] = ColorResolver.resolveSnake(snake.playerId)
            }
        }
        state.food.forEach { food ->
            cellsTmp[config.width * food.y + food.x] = ColorResolver.resolveFood(food.x, food.y)
        }
        playersState.value = state.players
        cells.value.clear()
        cells.value = cellsTmp
    }


//    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch(Dispatchers.IO) {
//        while (true) {
//            Thread.sleep(300)
//            val list = playersState.value.toMutableList()
//            list.shuffle()
//            println("dafafa")
//            playersState.value = list.toTypedArray()
//        }
//    }

    Row(modifier) {
        // Padding.
        val generalComponentPadding = 6.dp

        // General properties.
        val generalColumnModifier = Modifier.fillMaxHeight()
        val generalComponentsModifier = Modifier.padding(generalComponentPadding)

        // Column with game information.
        val gameInfoColumnModifier = generalColumnModifier.weight(.2f)

        Column(modifier = gameInfoColumnModifier) {
            Logo(generalComponentsModifier)

            Column(
                Modifier.fillMaxHeight(0.9f), verticalArrangement = Arrangement.Center
            ) {
                Rating(
                    generalComponentsModifier, config.playerName, players = playersState.value
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
                    action = { selected, isChecked -> println("$selected, $isChecked") })
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

    fun resolveSnake(id: Int): Color {
        val colorBase = colors[id.absoluteValue % colors.size]
        val offset = ((id.absoluteValue / colors.size) % 10) * 10 * if (id % 2 == 0) -1 else 1
        return Color(colorBase.red + offset, colorBase.green + offset, colorBase.blue + offset)
    }
}