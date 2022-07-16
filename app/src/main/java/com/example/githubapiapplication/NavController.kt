package com.example.githubapiapplication

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.navigation.findNavController

class NavController {
    private fun transToItem(item: ItemsGitHub): Bundle {
        val bundle = Bundle()
        bundle.putSerializable("Item",item)
        return (bundle)
    }

    fun routeToDetailFragment(item: ItemsGitHub, view: View){
        view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, transToItem(item))
    }
}