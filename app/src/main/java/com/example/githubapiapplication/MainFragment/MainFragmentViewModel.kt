package com.example.githubapiapplication.MainFragment

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.screens.Screens
import com.example.githubapiapplication.screens.Screens.router
import com.example.myapplicationapi.Data.Retrofit.Common
import kotlinx.coroutines.launch

class MainFragmentViewModel(
): ViewModel() {


    private val mService = Common()
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
         _list.postValue(mService.getItem(since))
        }
    }

    fun shared(item: ItemsGitHub): Intent? {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, item.html_url)
        intent.type = "text/plain"
        return Intent.createChooser(intent, "Share To:")
    }
}