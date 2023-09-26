package component

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import api.v1.dto.Announcement

@Composable
fun GameAnnouncementsList(modifier: Modifier, announcements: MutableList<Announcement>) {
    Card(
        modifier = modifier, elevation = 0.dp, backgroundColor = Color(240, 240, 240), shape = RoundedCornerShape(15.dp)
    ) {
        Box {
            val state = rememberLazyListState()
            LazyColumn(modifier = Modifier.fillMaxSize(), state = state, content = {
                items(announcements.size) { index ->
                    val announcement = announcements[index]
                    GameItemBlock(
                        games = announcement.games,
                        address = announcement.address,
                        onView = { address -> println(address) },
                        onJoin = { address ->  println(address) },
                        last = index == announcements.size - 1
                    )
                }
            })

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp).fillMaxHeight().width(8.dp),
                adapter = rememberScrollbarAdapter(
                    scrollState = state
                )
            )
        }
    }
}
