package com.example.githubapiapplication

import androidx.lifecycle.ViewModel
import com.example.githubapiapplication.screens.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

open class MainViewModel(
    val router: Router = Router()
) : ViewModel() {



    fun create() {
        router.newRootScreen(Screens.getMainFragment())
    }

}