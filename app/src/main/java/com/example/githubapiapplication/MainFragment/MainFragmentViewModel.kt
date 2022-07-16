package com.example.githubapiapplication.MainFragment

import android.content.Intent
import android.content.Intent.createChooser
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiapplication.ItemsGitHub
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel: ViewModel() {


    private val mService: RetrofitServices = Common.retrofitService
    private val _list: MutableLiveData<List<ItemsGitHub>> = MutableLiveData()
    val list: LiveData<List<ItemsGitHub>> = _list

    init {
        getAllItemList(0)
    }



    fun getAllItemList(since: Int) {
        mService.getItemList(since).enqueue(object : Callback<List<ItemsGitHub>> {
            override fun onFailure(call: Call<List<ItemsGitHub>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<ItemsGitHub>>, response: Response<List<ItemsGitHub>>) {
                viewModelScope.launch {
                    val listData: List<ItemsGitHub> = response.body()!!
                    _list.postValue(listData)
                }

            }
        })
    }
}