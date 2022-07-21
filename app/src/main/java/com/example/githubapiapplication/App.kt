package com.example.githubapiapplication

import android.app.Application
import com.example.githubapiapplication.di.appModule
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
      /*  INSTANCE = this*/
    }

   /* companion object {
        internal lateinit var INSTANCE: App
            private set
    }*/
}