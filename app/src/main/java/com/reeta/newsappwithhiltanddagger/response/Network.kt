package com.reeta.newsappwithhiltanddagger.response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    val BASE_URL="https://newsapi.org/"
    val country="us"
    val apiKey="dd5a27aa96e6435999947ed875aec407"

    fun getRetrofit()=
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getService()= getRetrofit().create(ApiCall::class.java)
}