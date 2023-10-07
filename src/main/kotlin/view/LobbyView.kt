package view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.GameAnnouncementsList
import component.GameStartButton
import component.GameStartDialog
import component.Logo
import model.LobbyController
import model.api.v1.dto.Announcement

@Composable
fun LobbyView(lobbyController: LobbyController) {
    val announcements = remember { mutableStateOf(mutableListOf<Announcement>()) }
    val openDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        lobbyController.setGameAnnouncementsListener { update: List<Announcement> ->
            announcements.value = update.toMutableList()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row {
            val generalComponentPadding = 6.dp
            val generalComponentsModifier = Modifier.padding(generalComponentPadding)

            val gameInfoColumnModifier = Modifier.fillMaxHeight().weight(.2f)
            val gameAnnouncementsColumnModifier = Modifier.fillMaxHeight().weight(.8f)

            Column(modifier = gameInfoColumnModifier) {
                Logo(generalComponentsModifier)
                GameStartButton(
                    modifier = generalComponentsModifier,
                    onClick = { openDialog.value = true }
                )
            }

            Column(
                modifier = gameAnnouncementsColumnModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GameAnnouncementsList(
                    controller = lobbyController,
                    modifier = generalComponentsModifier,
                    announcements = announcements.value
                )
            }
        }
    }

    if (openDialog.value) {
        GameStartDialog(openDialog) { gameName, playerName, config ->
            lobbyController.newGame(gameName, playerName, config)
        }
    }
}