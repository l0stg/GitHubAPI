package com.example.githubapiapplication.di

import com.example.githubapiapplication.MainActivity.MainViewModel
import com.example.githubapiapplication.MainFragment.MainFragmentViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }

    viewModel{MainFragmentViewModel(get())}
    viewModel{MainViewModel(get())}

}