package com.example.githubapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val navigatorHolder by inject<NavigatorHolder>()

    private val viewModel by viewModels<MainViewModel>()

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