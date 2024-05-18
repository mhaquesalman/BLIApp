package com.example.bliapp.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
            Text(text = "HackerNews")
        },
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