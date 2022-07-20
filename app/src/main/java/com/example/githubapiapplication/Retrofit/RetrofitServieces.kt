package com.example.myapplicationapi.Data.Retrofit

import com.example.githubapiapplication.ItemsGitHub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*


interface RetrofitServices {

    @GET("repositories")
    suspend fun getItemList(
        @Query("since") since: Int
    ): Response<List<ItemsGitHub>>
}

