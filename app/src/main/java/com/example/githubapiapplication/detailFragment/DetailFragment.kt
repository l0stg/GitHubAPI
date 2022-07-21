package com.example.githubapiapplication.detailFragment

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding

import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.MainFragment.MainFragment
import com.example.githubapiapplication.MainFragment.MainFragmentViewModel
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.FragmentDetailBinding

import com.github.terrakok.cicerone.Cicerone



class DetailesFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()

    companion object {
        private const val DATA = "DATA"
        fun getInstance(data: String) = DetailesFragment().apply {
            arguments = Bundle().apply {
                putSerializable(DATA, data)
            }
        }
    }

    private val data: String by lazy {
        (arguments?.getString(DATA)) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            myWebView.webViewClient = WebViewClient()
            myWebView.settings?.javaScriptEnabled = true
            binding.myWebView.loadUrl(data)
        }
    }
}