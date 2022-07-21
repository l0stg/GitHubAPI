package com.example.myapplicationapi.Data.Retrofit

import com.example.githubapiapplication.ItemsGitHub
import retrofit2.Response


class Common {
    suspend fun getItem(since: Int): List<ItemsGitHub>? = RetrofitClient.api.getItemList(since).body()
}