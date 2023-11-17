package com.gio.molcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.gio.molcomposer.ui.theme.ModernComposeTheme
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ModernCompose() {
    ModernComposeTheme {
        val (text, setValue) = remember {
            mutableStateOf("")
        }
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val keyboardController = LocalSoftwareKeyboardController.current
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = text, onValueChange = setValue)
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Hello $text")
                    }
                    keyboardController?.hide()
                }) {
                    Text(text = "클릭")
                }
            }
        }
    }
}