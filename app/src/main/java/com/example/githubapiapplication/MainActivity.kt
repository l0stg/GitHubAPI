package com.example.githubapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.githubapiapplication.MainFragment.MainFragment
import com.example.githubapiapplication.screens.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private val cicerone = Cicerone.create()
    private val viewModel by viewModels<MainViewModel>()
    private val navigatorHolder get() = cicerone.getNavigatorHolder()

    private val navigator by lazy {
        AppNavigator(this, R.id.contentFrame)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.create()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
}