package com.example.bliapp.components

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.bliapp.ui.theme.AppBarBg

@Composable
fun HomeScreenAppBar() {
    TopAppBar(
        title = {
            Text(text = "HackerNews", fontFamily = FontFamily.Cursive, fontSize = 26.sp)
        },
        contentColor = Color.White.copy(alpha = 0.7f),
        backgroundColor = AppBarBg
    )
}