package com.example.bliapp.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.bliapp.ui.theme.AppBarBg

@Composable
fun DetailScreenAppBar(
    goToHomeScreen: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = goToHomeScreen)
        },
        title = {
            Text(text = "HackerNews", fontFamily = FontFamily.Cursive, fontSize = 26.sp)
        },
        contentColor = Color.White.copy(0.7f),
        backgroundColor = AppBarBg
    )
}

@Composable
fun BackAction(
    onBackClicked: () -> Unit
) {
    IconButton(
        onClick = { onBackClicked() }
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow",)
    }
}