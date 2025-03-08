package com.example.weather.data.remote

import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("appid", apiKey)
            .build()
        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()
        return chain.proceed(requestWithApiKey)
    }
}

