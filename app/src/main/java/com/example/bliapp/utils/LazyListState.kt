package com.example.bliapp.utils

import androidx.compose.foundation.lazy.LazyListState

val LazyListState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
            layoutInfo.totalItemsCount - 1

val LazyListState.isFirstItemVisible: Boolean
    get() = firstVisibleItemIndex == 0

val LazyListState.lastVisibleItemIndex: Int?
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index

val LazyListState.totalItemsCount: Int
    get() = layoutInfo.totalItemsCount