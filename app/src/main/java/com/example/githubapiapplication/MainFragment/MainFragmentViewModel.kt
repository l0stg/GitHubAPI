package com.example.githubapiapplication.MainFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.screens.Screens
import com.example.myapplicationapi.Data.Retrofit.Common
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainFragmentViewModel(
    private val router: Router
): ViewModel() {

    private val mService = Common()
    private val _list: MutableLiveData<List<ItemsGitHub>> = MutableLiveData()
    val list: LiveData<List<ItemsGitHub>> = _list
    private val fakeList: List<ItemsGitHub> = listOf()

    init {
        loadData(0)
    }

    fun pullToRefresh(){
        loadData(0)
    }

    fun loadMoreData(){
        list.value?.last()?.id?.let { loadData(it) } ?: loadData(0)

    }

    private fun loadData(since: Int) {
        viewModelScope.launch {
            _list.postValue(mService.getItem(since))
        }
    }


    fun routeToDetail(url: String){
        router.navigateTo(Screens.getDetailFragment(url))
    }

    fun shared(item: ItemsGitHub): Intent? {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, item.html_url)
        intent.type = "text/plain"
        return Intent.createChooser(intent, "Share To:")
    }
}