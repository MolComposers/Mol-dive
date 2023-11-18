package com.gio.molcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gio.molcomposer.ui.theme.ModernComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModernCompose()
        }
    }
}

@Preview
@Composable
fun Preview() {
    ModernCompose()
}

@Composable
fun ModernCompose() {
    val viewModel = viewModel<MainViewModel>()
    ModernComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = viewModel.data.value, fontSize = 30.sp)
            Button(onClick = {
                viewModel.changeData("World")
            }) {
                Text(text = "변경")
            }
        }
    }
}

class MainViewModel : ViewModel() {
    private val _data = mutableStateOf("Hello")
    val data: State<String> = _data
    fun changeData(newData: String) {
        _data.value = newData
    }
}