package com.example.bliapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bliapp.TAG
import com.example.bliapp.models.News
import com.example.bliapp.ui.theme.AppBarBg
import com.example.bliapp.utils.LoadingFor
import com.example.bliapp.utils.RequestState
import com.example.bliapp.utils.isFirstItemVisible
import com.example.bliapp.utils.isLastItemVisible
import com.example.bliapp.utils.lastVisibleItemIndex
import com.example.bliapp.utils.totalItemsCount
import com.example.bliapp.viewmodels.NewsList

@Composable
fun NewsList(
    listType: String,
    news: RequestState<NewsList>,
    loadingFor: LoadingFor,
    isLoadingMore: Boolean,
    loadMore: () -> Unit,
    onNewsClicked: (Int) -> Unit
) {

    when (news) {
        is RequestState.Loading -> LinearProgressIndicator()
        is RequestState.Success -> {
            if (!news.data.isEmpty()) {
                if (listType == "TOP")
                    DisplayListForTopNews(
                        news.data,
                        loadingFor,
                        isLoadingMore,
                        loadMore,
                        onNewsClicked
                    )
                else
                    DisplayListForNewNews(
                        news.data,
                        loadingFor,
                        isLoadingMore,
                        loadMore,
                        onNewsClicked
                    )
            }
        }
        is RequestState.Error -> Text(text = "Something Error!")
        is RequestState.Idle -> {}
    }
}

@Composable
fun DisplayListForTopNews(
    news: List<News>,
    loadingFor: LoadingFor,
    isLoadingMore: Boolean,
    loadMore: () -> Unit,
    onNewsClicked: (Int) -> Unit
) {

    val lazyListState = rememberLazyListState()

    val scrollToLastPosition by remember(news.size) {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == news.size - 2
        }
    }


    if (scrollToLastPosition) {
        Log.d(TAG, "loadmore: ${scrollToLastPosition} called")
        loadMore()
    }

    Row(
        modifier = Modifier
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            state = lazyListState,
            userScrollEnabled = !isLoadingMore,
            modifier = Modifier.weight(9f)
        ) {
            items(
                items = news,
                key = {
                    it.id!!
                }
            ) { newsItem ->
                NewsItem(
                    news = newsItem,
                    isLoadingMore = isLoadingMore,
                    onNewsClicked = onNewsClicked
                )
            }
        }
        if (isLoadingMore && loadingFor == LoadingFor.TOP)
            CircularProgressIndicator(
                modifier = Modifier.weight(1f),
                color = AppBarBg
            )
    }
}

@Composable
fun DisplayListForNewNews(
    news: List<News>,
    loadingFor: LoadingFor,
    isLoadingMore: Boolean,
    loadMore: () -> Unit,
    onNewsClicked: (Int) -> Unit
) {

    val lazyListState = rememberLazyListState()

    val scrollToLastPosition by remember(news.size) {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == news.size - 2
        }
    }

    if (scrollToLastPosition) {
//        Log.d(TAG, "loadmore: ${scrollToLastPosition} called")
//        loadMore()
    }

    Row(
        modifier = Modifier
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            state = lazyListState,
            modifier = Modifier.weight(9f)
        ) {
            items(
                items = news,
                key = {
                    it.id!!
                }
            ) { newsItem ->
                NewsItem(
                    news = newsItem,
                    isLoadingMore = isLoadingMore,
                    onNewsClicked = onNewsClicked
                )
            }
        }
        if (isLoadingMore && loadingFor == LoadingFor.NEW)
            CircularProgressIndicator(
                modifier = Modifier.weight(1f),
                color = AppBarBg
            )
    }
}

