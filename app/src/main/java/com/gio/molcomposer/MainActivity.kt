package com.gio.molcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = "first"
        ) {
            composable("first") {
                FirstScreen(navController)
            }
            composable("second") {
                SecondScreen(navController)
            }
            composable("third/{value}") { backStackEntry ->
                ThirdScreen(
                    navController = navController,
                    value = backStackEntry.arguments?.getString("value") ?: ""
                )
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavHostController) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "첫 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(route = "second") }) {
            Text(text = "두 번째!")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if (value.isNotEmpty()) {
                navController.navigate(route = "third/$value")
            }
        }) {
            Text(text = "세 번째!")
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "두 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text(text = "뒤로 가기")
        }
    }
}

@Composable
fun ThirdScreen(navController: NavHostController, value: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "세 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = value)
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "뒤로 가기")
        }
    }
}