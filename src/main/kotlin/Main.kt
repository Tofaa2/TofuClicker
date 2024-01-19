import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

val clicker: Clicker = Clicker();

@Composable
@Preview
fun App() {

    var cps by remember { mutableStateOf(0) }
    var doubleClick by remember { mutableStateOf(false) }
    var randomization by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(false) }

    MaterialTheme {

        Column {
            Button(
                onClick = {
                    if (enabled) {
                        clicker.stop()
                        enabled = false
                    }
                    else {
                        clicker.start()
                        enabled = true
                    }
                }
            ) {
                Text("Left Clicker: " + if (enabled) "Enabled" else "Disabled")
            }

            Slider(
                value = cps.toFloat(),
                onValueChange = { cps = it.toInt() },
                valueRange = 0f..100F,
                steps = 1,
                onValueChangeFinished = {
                    clicker.cps = cps
                }
            )
            Text("CPS: $cps")
            Button(
                onClick = {
                    if (doubleClick) {
                        clicker.doubleClick = false
                        doubleClick = false
                    }
                    else {
                        clicker.doubleClick = true
                        doubleClick = true
                    }
                }
            )
            {
                Text("Double Click: " + if (doubleClick) "Enabled" else "Disabled")
            }

            Button(
                onClick = {
                    if (randomization) {
                        clicker.randomization = false
                        randomization = false
                    }
                    else {
                        clicker.randomization = true
                        randomization = true
                    }
                }
            )
            {
                Text("Randomization: " + if (randomization) "Enabled" else "Disabled")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
