package com.example.bliapp.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bliapp.TAG
import com.example.bliapp.components.HomeScreenAppBar
import com.example.bliapp.components.NewsList
import com.example.bliapp.navigation.AppScreens
import com.example.bliapp.utils.LoadingFor
import com.example.bliapp.utils.RequestState
import com.example.bliapp.viewmodels.NewsList
import com.example.bliapp.viewmodels.NewsViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    newsViewModel: NewsViewModel
) {

    val topNews: RequestState<NewsList> by newsViewModel.topNews.collectAsState()
    val newNews: RequestState<NewsList> by newsViewModel.newNews.collectAsState()


    var isFetched by rememberSaveable { mutableStateOf(false) }
    var skip by remember { mutableIntStateOf(0) }
    var loadingFor by remember { mutableStateOf(LoadingFor.NONE) }

    LaunchedEffect(key1 = true) {
        if (!isFetched) {
            newsViewModel.getNewNews()
            newsViewModel.getTopNews(skip)
        }
        isFetched = true
    }


    Scaffold(
        topBar = {
            HomeScreenAppBar()
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = "New News", style = MaterialTheme.typography.h4, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.height(20.dp))
            NewsList(
                listType = "NEW",
                news = newNews,
                isLoadingMore = newsViewModel.loadMore.value,
                loadingFor = loadingFor,
                loadMore = {},
                onNewsClicked = { id ->
                    val detailScreen = AppScreens.DetailScreen.name
                    navController.navigate(route = "$detailScreen/" + id)
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Top News",
                style = MaterialTheme.typography.h4,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(20.dp))
            NewsList(
                listType = "TOP",
                news = topNews,
                loadingFor = loadingFor,
                isLoadingMore = newsViewModel.loadMore.value,
                loadMore = {
                    if (!newsViewModel.loadMore.value) {
                        skip += 5
                        loadingFor = LoadingFor.TOP
                        Log.d(TAG, "HomeScreen: $skip")
                        newsViewModel.getTopNews(skip)
                    }
                },
                onNewsClicked = { id ->
                    val detailScreen = AppScreens.DetailScreen.name
                    navController.navigate(route = "$detailScreen/" + id)
                }
            )
        }
    }

}