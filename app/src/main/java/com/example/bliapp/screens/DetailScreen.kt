package com.example.bliapp.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.bliapp.components.DetailScreenAppBar
import com.example.bliapp.components.NewsContent
import com.example.bliapp.models.News
import com.example.bliapp.utils.RequestState
import com.example.bliapp.viewmodels.NewsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    navController: NavController,
    newsViewModel: NewsViewModel,
    newsId: Int
) {

    val selectedNews: RequestState<News> by newsViewModel.selectedNews.collectAsState()
    val context = LocalContext.current
    
    LaunchedEffect(key1 = Unit) {
        newsViewModel.getNewsById(newsId)
    }

    Scaffold(
        topBar = {
            DetailScreenAppBar {
                navController.popBackStack()
            }
        }
    ) {
        NewsContent(selectedNews = selectedNews) { newsUrl ->
            val uri = Uri.parse(newsUrl)
            val mIntent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(mIntent)

        }
    }
}