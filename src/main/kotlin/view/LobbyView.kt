package view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import api.v1.dto.*
import component.GameAnnouncementsList
import component.GameStartButton
import component.Logo
import model.GameConfig
import java.net.InetSocketAddress

@Composable
fun LobbyView() {
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
            GameStartButton(generalComponentsModifier, { println("Начать игру") })
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
                announcements = getAnnos()
            )
        }
    }
}

fun getAnnos(): MutableList<Announcement> {
    return mutableListOf(
        Announcement(
            InetSocketAddress(333),
            32,
            arrayOf(
                Game(
                    GameConfig(
                        "Sample game",
                        "Petya",
                        34,
                        321,
                        3,
                        3441
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Sanya",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(36513),
            323,
            arrayOf(
                Game(
                    GameConfig(
                        "Another game",
                        "Petya",
                        53,
                        11, 333,
                        31
                    ),
                    false,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            0,
                            "192.168.31.353",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            34,
                            "192.168.67.32",
                            "Sanya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                ),
                Game(
                    GameConfig(
                        "Yet another game",
                        "Polya",
                        44,
                        44, 3112,
                        312
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(333),
            32,
            arrayOf(
                Game(
                    GameConfig(
                        "Sample game",
                        "Petya",
                        34,
                        321,
                        3,
                        3441
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Sanya",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(36513),
            323,
            arrayOf(
                Game(
                    GameConfig(
                        "Another game",
                        "Petya",
                        53,
                        11, 333,
                        31
                    ),
                    false,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            0,
                            "192.168.31.353",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            34,
                            "192.168.67.32",
                            "Sanya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                ),
                Game(
                    GameConfig(
                        "Yet another game",
                        "Polya",
                        44,
                        44, 3112,
                        312
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(333),
            32,
            arrayOf(
                Game(
                    GameConfig(
                        "Sample game",
                        "Petya",
                        34,
                        321,
                        3,
                        3441
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Sanya",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(36513),
            323,
            arrayOf(
                Game(
                    GameConfig(
                        "Another game",
                        "Petya",
                        53,
                        11, 333,
                        31
                    ),
                    false,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            0,
                            "192.168.31.353",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            34,
                            "192.168.67.32",
                            "Sanya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                ),
                Game(
                    GameConfig(
                        "Yet another game",
                        "Polya",
                        44,
                        44, 3112,
                        312
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(333),
            32,
            arrayOf(
                Game(
                    GameConfig(
                        "Sample game",
                        "Petya",
                        34,
                        321,
                        3,
                        3441
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            341,
                            "192.168.232.12",
                            "Sanya",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(36513),
            323,
            arrayOf(
                Game(
                    GameConfig(
                        "Another game",
                        "Petya",
                        53,
                        11, 333,
                        31
                    ),
                    false,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            0,
                            "192.168.31.353",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            34,
                            "192.168.67.32",
                            "Sanya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                ),
                Game(
                    GameConfig(
                        "Yet another game",
                        "Polya",
                        44,
                        44, 3112,
                        312
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(36513),
            323,
            arrayOf(
                Game(
                    GameConfig(
                        "Another game",
                        "Petya",
                        53,
                        11, 333,
                        31
                    ),
                    false,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            0,
                            "192.168.31.353",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            34,
                            "192.168.67.32",
                            "Sanya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                ),
                Game(
                    GameConfig(
                        "Yet another game",
                        "Polya",
                        44,
                        44, 3112,
                        312
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                )
            )
        ),
        Announcement(
            InetSocketAddress(36513),
            323,
            arrayOf(
                Game(
                    GameConfig(
                        "Another game",
                        "Petya",
                        53,
                        11, 333,
                        31
                    ),
                    false,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            0,
                            "192.168.31.353",
                            "Kolya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.NORMAL,
                            PlayerType.HUMAN,
                            34,
                            "192.168.67.32",
                            "Sanya",
                            3423
                        ),
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                ),
                Game(
                    GameConfig(
                        "Yet another game",
                        "Polya",
                        44,
                        44, 3112,
                        312
                    ),
                    true,
                    arrayOf(
                        Player(
                            33,
                            NodeRole.MASTER,
                            PlayerType.HUMAN,
                            34,
                            "192.168.13.134",
                            "Dasha",
                            3423
                        )
                    )
                )
            )
        )
    )
}