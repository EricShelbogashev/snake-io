package view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.GameAnnouncementsList
import component.GameStartButton
import component.GameStartDialog
import component.Logo
import controller.LobbyController
import model.GameConfig

@Composable
fun LobbyView(lobbyController: LobbyController) {
    val modifier = Modifier
        .fillMaxSize()

//    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch(Dispatchers.IO) {
//        while (true) {
//            Thread.sleep(3000)
//            val list = playersState.value.toMutableList()
//            list.shuffle()
//            playersState.value = list.toTypedArray()
//        }
//    }


    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        GameStartDialog(openDialog) { config: GameConfig ->
            lobbyController.newGame(config)
        }
    }

    Row(modifier) {
        // Padding.
        val generalComponentPadding = 6.dp

        // General properties.
        val generalColumnModifier = Modifier.fillMaxHeight()
        val generalComponentsModifier = Modifier
            .padding(generalComponentPadding)

        // Column with game menu.
        val gameInfoColumnModifier = generalColumnModifier
            .weight(.2f)

        Column(modifier = gameInfoColumnModifier) {
            Logo(generalComponentsModifier)
            GameStartButton(generalComponentsModifier) {
                openDialog.value = true
            }
        }

        // Column with game announcements.
        val gameAnnouncementsColumnModifier = generalColumnModifier
            .weight(.8f)

        Column(
            modifier = gameAnnouncementsColumnModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GameAnnouncementsList(
                modifier = generalComponentsModifier,
                announcements = mutableListOf()
            )
        }
    }
}