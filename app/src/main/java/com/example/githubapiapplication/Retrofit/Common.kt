package com.example.myapplicationapi.Data.Retrofit

object Common {
    private val BASE_URL = "https://api.github.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}