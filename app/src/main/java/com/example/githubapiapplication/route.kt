package com.example.githubapiapplication

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

val cicerone = Cicerone.create()
val router get() = cicerone.router
val navigatorHolder get() = cicerone.getNavigatorHolder()


