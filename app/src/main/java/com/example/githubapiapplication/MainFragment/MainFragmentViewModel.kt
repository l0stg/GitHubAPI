package com.example.githubapiapplication.MainFragment

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.screens.Screens
import com.example.githubapiapplication.screens.Screens.router
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel(
): ViewModel() {


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

    fun shared(item: ItemsGitHub): Intent? {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, item.html_url)
        intent.type = "text/plain"
        return Intent.createChooser(intent, "Share To:")
    }
}