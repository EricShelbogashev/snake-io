import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import component.Chip
import res.GameIcon

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SnakeGame",
        state = rememberWindowState(width = 720.dp, height = 680.dp)
    ) {
    }
}


//@Preview
//@Composable
//fun LobbyScreen() {
//    Column(
//        Modifier.fillMaxSize().background(Color(200, 202, 181))
//    ) {
//        Box(Modifier.fillMaxWidth()) {
//            Row(Modifier.align(Alignment.CenterStart)) {
//                Text(
//                    text = "Searching for games",
//                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
//                    fontSize = 18.sp,
//                    style = TextStyle(
//                        fontWeight = FontWeight.Bold,
//                        color = Color(42, 30, 17),
//                        fontFamily = FontFamily.Monospace
//                    )
//                )
//
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .size(36.dp)
//                        .align(Alignment.CenterVertically)
//                        .padding(start = 16.dp, top = 8.dp),
//                    color = Color(85, 75, 65), // Customize the color if needed
//                    strokeWidth = 3.dp // Customize the stroke width if needed
//                )
//            }
//
//            Button(
//                onClick = {
//                    TODO()
//                },
//                modifier = Modifier.align(Alignment.CenterEnd).padding(top = 8.dp, end = 8.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color(237, 237, 230))
//            ) {
//                Text(
//                    text = "Create new game", style = TextStyle(
//                        fontWeight = FontWeight.W600, // Set the text to bold
//                        fontSize = 16.sp, // Customize the font size
//                        color = Color(42, 30, 17),
//                        fontFamily = FontFamily.Monospace
//                    )
//                )
//            }
//        }
//
//        Box(
//            modifier = Modifier.fillMaxSize()
//                .padding(start = 8.dp, top = 8.dp, end = 6.dp, bottom = 6.dp)
//        ) {
//            val state = rememberLazyListState()
//            DynamicUpdateLazyColumnScreen()
//            VerticalScrollbar(
//                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().width(10.dp),
//                adapter = rememberScrollbarAdapter(
//                    scrollState = state
//                )
//            )
//        }
//    }
//}
//
//@Composable
//fun DynamicUpdateLazyColumnScreen() {
//    // Create a mutable state for the item list
//    var itemList by remember { mutableStateOf(generateItemList()) }
//    var newItemText by remember { mutableStateOf("") }
//    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch(Dispatchers.Default) {
//        while (true) {
//            var i = 50
//            Thread.sleep(1000)
//            launch(Dispatchers.Default) {
//                itemList += i++.toString()
//            }
//        }
//    }
//
//    MaterialTheme(
//        colors = darkColors(),
//        typography = Typography(defaultFontFamily = FontFamily.Default)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.Start
//        ) {
//            // Text field to enter a new item
//            BasicTextField(
//                value = newItemText,
//                onValueChange = { newItemText = it },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .background(Color.Black),
//                textStyle = TextStyle(color = MaterialTheme.colors.primary)
//            )
//
//            // Button to add a new item
//            Button(
//                onClick = {
//                    if (newItemText.isNotBlank()) {
//                        // Add the new item to the list
//                        itemList = itemList + newItemText
//                        newItemText = ""
//                    }
//                },
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = "Add Item")
//            }
//
//            // LazyColumn to display the list of items
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                items(itemList) { item ->
//                    ListItem(item = item)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ListItem(item: String) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(text = item)
//        }
//    }
//}
//
//// Function to generate an initial list of items
//fun generateItemList(): List<String> {
//    return List(1) { index ->
//        "Item $index"
//    }
//}
//
