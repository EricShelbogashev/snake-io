import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameField(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "GameField")
    }
}

@Preview
@Composable
private fun PreviewGameField() {
//    GameField()
    ResponsiveGrid()
}

@Composable
fun ResponsiveGrid() {
    val items = List(20) { index -> index } // Replace with your data

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(items.size) { index ->
            GridItem(index = index)
        }
    }
}

@Composable
fun GridItem(index: Int) {
    // Your grid item content here
    // You can use Card, Box, or any other Composables as grid items
    // Example:
    // Card(
    //     modifier = Modifier.padding(8.dp),
    // ) {
    //     Text(text = "Item $index")
    // }
}

@Composable
fun MainScreen() {
    // Your main screen content here
    // Example:
    // Scaffold(
    //     modifier = Modifier.fillMaxSize(),
    // ) {
    //     ResponsiveGrid()
    // }
}

// Call MainScreen() in your main activity or composable function.

