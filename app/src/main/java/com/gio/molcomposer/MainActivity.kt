package com.gio.molcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    ModernComposeTheme {
        val viewModel = viewModel<MainViewModel>()
        val text: State<String> = viewModel.value.observeAsState("Initial")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text.value)
            Button(onClick = { }) {
                Text("클릭")
            }
            TextField(value = text.value, onValueChange = { viewModel.changeValue(newValue = it) })
        }
    }
}

class MainViewModel : ViewModel() {
    private val _value = MutableLiveData("Hello World")
    val value: LiveData<String> = _value
    fun changeValue(newValue: String) {
        _value.value = newValue
    }
}