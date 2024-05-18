package com.example.bliapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bliapp.navigation.AppNavigation
import com.example.bliapp.repositories.NewsRepository
import com.example.bliapp.ui.theme.BLIAppTheme
import com.example.bliapp.viewmodels.NewsViewModel
import com.example.bliapp.viewmodels.NewsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "BLI-App"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val newsViewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory())
    BLIAppTheme {
        AppNavigation(newsViewModel = newsViewModel)
    }
}

