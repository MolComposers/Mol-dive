package com.gio.molcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gio.molcomposer.ui.theme.ModernComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWork()
        }
    }
}

@Composable
fun HomeWork() {
    ModernComposeTheme {
        val viewModel: MainViewModel = viewModel()
        SelectableIconCard(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .padding(16.dp),
            radius = 10.dp,
            elevation = 10.dp,
            backgroundResId = R.drawable.dani,
            contentDescription = "마이멜로디",
            isSelected = viewModel.isFavorite.value,
            onIconClick = { viewModel.reverseIsFavorite() },
            unselectedIcon = Icons.Default.FavoriteBorder,
            selectedIcon = Icons.Default.Favorite,
            iconDescription = "좋아요",
            iconTint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeWork()
}

@Composable
fun SelectableIconCard(
    modifier: Modifier,
    radius: Dp,
    elevation: Dp,
    backgroundResId: Int,
    contentDescription: String,
    isSelected: Boolean,
    onIconClick: () -> Unit,
    unselectedIcon: ImageVector,
    selectedIcon: ImageVector,
    iconDescription: String,
    iconTint: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(radius),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = backgroundResId),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = { onIconClick() }) {
                    Icon(
                        modifier = Modifier.padding(12.dp),
                        imageVector = if (isSelected) selectedIcon else unselectedIcon,
                        contentDescription = iconDescription,
                        tint = iconTint
                    )
                }
            }
        }
    }
}