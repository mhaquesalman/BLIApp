package com.example.bliapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bliapp.models.News
import com.example.bliapp.utils.RequestState
import com.example.bliapp.viewmodels.NewsList

@Composable
fun NewsList(
    news: RequestState<NewsList>,
    onNewsClicked: (Int) -> Unit
) {

    when (news) {
        is RequestState.Loading -> LinearProgressIndicator()
        is RequestState.Success -> {
            if (!news.data.isEmpty())
                DisplayList(news.data, onNewsClicked)
        }

        is RequestState.Error -> Text(text = "Something Error!")
        is RequestState.Idle -> {}
    }
}

@Composable
fun DisplayList(
    news: List<News>,
    onNewsClicked: (Int) -> Unit
) {
    LazyRow {
        items(
            items = news,
            key = {
                it.id!!
            }
        ) { newsItem ->
            NewsItem(news = newsItem, onNewsClicked = onNewsClicked)
        }
    }
}
