package com.example.bliapp.components

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.bliapp.ui.theme.AppBarBg

@Composable
fun HomeScreenAppBar() {
    TopAppBar(
        title = {
            Text(text = "HackerNews")
        },
        backgroundColor = AppBarBg
    )
}