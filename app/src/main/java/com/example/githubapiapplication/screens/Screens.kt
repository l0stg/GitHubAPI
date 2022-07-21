package com.example.githubapiapplication.screens
import com.example.githubapiapplication.MainFragment.MainFragment
import com.example.githubapiapplication.detailFragment.DetailesFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.FragmentScreen



object Screens {

    fun getDetailFragment(url: String) = FragmentScreen { DetailesFragment.getInstance(url) }

    fun getMainFragment() =  FragmentScreen { MainFragment() }

}