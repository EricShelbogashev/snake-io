package view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.GameAnnouncementsList
import component.GameStartButton
import component.GameStartDialog
import component.Logo
import model.LobbyController
import model.api.v1.dto.Announcement
import model.api.v1.dto.GameConfig

@Composable
fun LobbyView(lobbyController: LobbyController) {
    val modifier = Modifier
        .fillMaxSize()

    val announcements = remember { mutableStateOf(listOf<Announcement>()) }
    LaunchedEffect(Unit) {
        lobbyController.setGameAnnouncementsListener { update: List<Announcement> ->
            announcements.value = update
        }
    }

    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        GameStartDialog(openDialog) { gameName: String, playerName: String, config: GameConfig ->
            lobbyController.newGame(gameName, playerName, config)
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
                announcements = announcements.value.toList()
            )
        }
    }
}