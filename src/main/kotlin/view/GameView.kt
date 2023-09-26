package view

import GameSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import api.v1.dto.NodeRole
import api.v1.dto.Player
import api.v1.dto.PlayerType
import component.GridField
import component.Logo
import component.Rating
import component.Stats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.GameConfig

@Composable
fun GameView(gameConfig: GameConfig) {
    val modifier = Modifier
        .fillMaxSize()

    val playersState = remember {
        mutableStateOf(
            arrayOf(
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 96, "192.154.34.34", "petya", 33),
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 21, "192.154.34.34", "petya", 33),
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 23, "192.154.34.34", "Petya", 32),
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 47, "192.154.34.34", "sonya", 65),
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 76, "192.154.34.34", "sonya", 25),
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 3, "192.154.34.34", "sonya", 55),
                Player(300, NodeRole.NORMAL, PlayerType.HUMAN, 31, "192.154.34.34", "dasha", 34),
            )
        )
    }

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch(Dispatchers.IO) {
        while (true) {
            Thread.sleep(3000)
            val list = playersState.value.toMutableList()
            list.shuffle()
            playersState.value = list.toTypedArray()
        }
    }

    Row(modifier) {
        // Padding.
        val generalComponentPadding = 6.dp

        // General properties.
        val generalColumnModifier = Modifier.fillMaxHeight()
        val generalComponentsModifier = Modifier
            .padding(generalComponentPadding)

        // Column with game information.
        val gameInfoColumnModifier = generalColumnModifier
            .weight(.2f)

        Column(modifier = gameInfoColumnModifier) {
            Logo(generalComponentsModifier)

            Column(
                Modifier.fillMaxHeight(0.9f),
                verticalArrangement = Arrangement.Center
            ) {
                Rating(
                    generalComponentsModifier,
                    gameConfig.playerName,
                    players = playersState.value
                )

                Stats(
                    generalComponentsModifier,
                    gameConfig.height,
                    gameConfig.width,
                    gameConfig.foodStatic,
                    gameConfig.stateDelayMs
                )
            }
        }

        // Column with game field.
        val gameFieldColumnModifier = generalColumnModifier
            .weight(.6f)
            .background(Color(240, 240, 240))

        Column(
            modifier = gameFieldColumnModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GridField(modifier = generalComponentsModifier, gameConfig)
        }

        // Column with game settings.
        val gameSettingsColumnModifier = generalColumnModifier
            .weight(.2f)
        Column(modifier = gameSettingsColumnModifier) {
            Column(
                Modifier.fillMaxHeight(0.9f),
                verticalArrangement = Arrangement.Center
            ) {
                GameSettings(
                    modifier = generalComponentsModifier,
                    action = { selected, isChecked -> println("$selected, $isChecked") }
                )
            }
        }
    }
}