package com.example.myapplicationapi.Data.Retrofit

import com.example.githubapiapplication.ItemsGitHub
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("repositories")
    fun getItemList(
        @Query("since") since: Int
    ): Call<List<ItemsGitHub>>
}