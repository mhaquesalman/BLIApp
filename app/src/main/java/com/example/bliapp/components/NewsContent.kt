package com.example.bliapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.bliapp.models.News
import com.example.bliapp.utils.RequestState
import com.example.bliapp.utils.formatDate

@Composable
fun NewsContent(
    selectedNews: RequestState<News>,
    readNews: (String) -> Unit
) {

    when (selectedNews) {
        is RequestState.Loading -> LinearProgressIndicator()
        is RequestState.Success -> {
            DisplayContent(data = selectedNews.data, readNews = readNews)
        }
        is RequestState.Error -> Text(text = "Something Error!")
        is RequestState.Idle -> {}
    }

}

@Composable
fun DisplayContent(data: News, readNews: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = data.title!!,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            val authorString = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Posted By: ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(data.author)
                }
            }
            Text(
                text = authorString
            )
            Spacer(modifier = Modifier.height(20.dp))
            val publishedString = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Posted On: ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(formatDate(data.publishedTime!!))
                }
            }
            Text(
                text = publishedString,
            )

            data.url?.let {
                val urlString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                        append("Read more")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ClickableText(text = urlString,
                    onClick = {
                        readNews(data.url)
                    })
            }

        }
    }
}
