package com.example.githubapiapplication.screens
import com.example.githubapiapplication.MainFragment.MainFragment
import com.example.githubapiapplication.detailFragment.DetailesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun getDetailFragment(urlit: String) = FragmentScreen { DetailesFragment.getInstance(urlit) }

    fun getMainFragment() = FragmentScreen { MainFragment.newInstance() }

}