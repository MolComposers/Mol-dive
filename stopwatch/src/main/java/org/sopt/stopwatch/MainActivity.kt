@file:OptIn(ExperimentalMaterial3Api::class)

package org.sopt.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.stopwatch.ui.theme.ModernComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModernComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel()
                    MainScreen(sec = viewModel.sec.value,
                        mill = viewModel.mill.value,
                        isRunning = viewModel.isRunning.value,
                        laps = viewModel.laps.value,
                        onReset = { viewModel.reset() },
                        onStart = { viewModel.start() },
                        onPause = { viewModel.pause() },
                        onLogLap = { viewModel.logLap() })
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    sec: Int,
    mill: Int,
    isRunning: Boolean,
    laps: List<String>,
    onReset: () -> Unit,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onLogLap: () -> Unit,
) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "$sec", fontSize = 100.sp)
                    Text(text = "$mill")
                }
                Spacer(modifier = Modifier.size(16.dp))

                Column(Modifier.verticalScroll(rememberScrollState())) {
                    laps.forEach { lap ->
                        Text(text = lap)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(onClick = { onReset() }, containerColor = Color.Red) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_refresh),
                        colorFilter = ColorFilter.tint(color = Color.White),
                        contentDescription = "Reset"
                    )
                }

                FloatingActionButton(
                    onClick = { if (isRunning) onPause() else onStart() },
                    containerColor = Color.Green,
                ) {
                    Image(
                        painter = if (isRunning) painterResource(id = R.drawable.ic_pause) else painterResource(
                            id = R.drawable.ic_play_arrow
                        ),
                        colorFilter = ColorFilter.tint(color = Color.White),
                        contentDescription = if (isRunning) "Pause" else "Start"
                    )
                }

                Button(onClick = { onLogLap() }) {
                    Text(text = "Log Lap")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ModernComposeTheme {
        val viewModel: MainViewModel = viewModel()
        MainScreen(sec = viewModel.sec.value,
            mill = viewModel.mill.value,
            isRunning = viewModel.isRunning.value,
            laps = viewModel.laps.value,
            onReset = { viewModel.reset() },
            onStart = { viewModel.start() },
            onPause = { viewModel.pause() },
            onLogLap = { viewModel.logLap() })
    }
}