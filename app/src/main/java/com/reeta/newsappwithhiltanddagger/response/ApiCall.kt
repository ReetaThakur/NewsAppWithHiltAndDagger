package com.reeta.newsappwithhiltanddagger.response

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("v2/top-headlines")
    suspend fun getInstance(@Query("country") country:String,
                            @Query("apiKey") apiKey:String):ResponseDTO
}