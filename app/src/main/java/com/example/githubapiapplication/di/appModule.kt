package com.example.githubapiapplication.di

import com.example.githubapiapplication.MainFragment.MainFragmentViewModel
import com.example.githubapiapplication.MainViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }


   /* viewModel{ MainViewModel(get()) }
    viewModel{ MainFragmentViewModel(get()) }*/


}