package com.example.githubapiapplication.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.githubapiapplication.R
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : KoinComponent, AppCompatActivity() {
    private val navigatorHolder by inject<NavigatorHolder>()
    private val viewModel by viewModels<MainViewModel>()


    private val navigator = AppNavigator(this, R.id.contentFrame)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.create()
        setContentView(R.layout.activity_main)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}