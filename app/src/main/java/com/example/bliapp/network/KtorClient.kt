package com.example.bliapp.network


import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
//        explicitNulls = false
    }

    val httpClient = HttpClient(Android) {

        install(HttpTimeout) {
            socketTimeoutMillis = 30 * 1000
            requestTimeoutMillis = 30 * 1000
            connectTimeoutMillis = 30 * 1000
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {

                }

            }
        }

        install(ContentNegotiation) {
            json(json)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
//            headers.append("Content-Type", "application/json")
        }

    }
}