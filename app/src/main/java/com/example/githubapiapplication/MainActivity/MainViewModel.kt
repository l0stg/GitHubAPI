package com.example.githubapiapplication.MainActivity

import androidx.lifecycle.ViewModel
import com.example.githubapiapplication.screens.Screens
import com.github.terrakok.cicerone.Router


class MainViewModel(
    private val router: Router
) : ViewModel() {

    fun create() {
        router.newRootScreen(Screens.getMainFragment())
    }

}