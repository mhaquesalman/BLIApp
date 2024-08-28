package com.example.bliapp.repositories

import android.util.Log
import com.example.bliapp.TAG
import com.example.bliapp.models.News
import com.example.bliapp.network.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NewsRepository {

    suspend fun getTopNews(skip: Int): Flow<List<News>> {
        val topNewsItem = mutableListOf<News>()
        val topNews =
            KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty")
                .body<List<Int>>()
//        Log.d(TAG, "getTopNews: ${topNews.size}")
//        topNews.forEach {
//            val topNewsItemResponse =
//                    KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/item/${it}.json?print=pretty")
//                        .body<News>()
//            topNewsItem.add(topNewsItemResponse)
//        }

        for ((index, value) in topNews.withIndex()) {
            if (index < skip) continue
            if (topNewsItem.size == 5) break

            val topNewsItemResponse =
                    KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/item/${value}.json?print=pretty")
                        .body<News>()
            topNewsItem.add(topNewsItemResponse)
        }
//        val topNewsItemResponse =
//            KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/item/${topNews[10]}.json?print=pretty")
//                .body<News>()


//        Log.d(TAG, "getTopNewsItem 1: ${topNewsItem.get(0)}")

        return flow {
            emit(topNewsItem)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getNewNews(): Flow<List<News>> {
        val newNewsItem = mutableListOf<News>()
        val newNews =
            KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty")
                .body<List<Int>>()
//        Log.d(TAG, "getNewNews: ${newNews.size}")

        for (i in newNews) {
            if (newNewsItem.size == 10) break
            val newNewsItemResponse =
                KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/item/${i}.json?print=pretty")
                    .body<News>()
            newNewsItem.add(newNewsItemResponse)
        }

        return flow {
            emit(newNewsItem)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNewsById(id: Int): Flow<News> {
        val newNewsItemResponse =
            KtorClient.httpClient.get("https://hacker-news.firebaseio.com/v0/item/${id}.json?print=pretty")
                .body<News>()

        return flow {
            emit(newNewsItemResponse)
        }.flowOn(Dispatchers.IO)
    }


}