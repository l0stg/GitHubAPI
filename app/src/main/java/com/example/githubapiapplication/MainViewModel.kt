package com.example.githubapiapplication

import androidx.lifecycle.ViewModel
import com.example.githubapiapplication.screens.Screens
import com.example.githubapiapplication.screens.router

import com.github.terrakok.cicerone.Router
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

open class MainViewModel(
) : ViewModel() {

    fun create() {
        router.newRootScreen(Screens.getMainFragment())
    }

}