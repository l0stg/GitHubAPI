package com.example.githubapiapplication.MainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.MainViewModel
import com.example.githubapiapplication.screens.Screens
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel(): MainViewModel() {

    private val mService: RetrofitServices = Common.retrofitService
    private val _list: MutableLiveData<List<ItemsGitHub>> = MutableLiveData()
    val list: LiveData<List<ItemsGitHub>> = _list


    init {
        getAllItemList(0)
    }

    fun routeToDetail(url: String){
        router.navigateTo(Screens.getDetailFragment(url))
    }

    fun getAllItemList(since: Int) {
        viewModelScope.launch {
            mService.getItemList(since).enqueue(object : Callback<List<ItemsGitHub>> {
                override fun onFailure(call: Call<List<ItemsGitHub>>, t: Throwable) {
                }
                override fun onResponse(call: Call<List<ItemsGitHub>>, response: Response<List<ItemsGitHub>>
                ) {
                    val listData: List<ItemsGitHub> = response.body()!!
                    _list.postValue(listData)
                }
            })
        }
    }
}