package com.example.myapplicationapi.Data.Retrofit

import android.util.Log
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.Owner
import retrofit2.Response


class Common {
    suspend fun getItem(since: Int): List<ItemsGitHub> {
        val itemsList: List<ItemsGitHub>?
        val response = RetrofitClient.api.getItemList(since)
        val body = response.body()
        if (body != null) {
            itemsList = body
        } else {
            itemsList = listOf()
        }
        return itemsList
    }
}
