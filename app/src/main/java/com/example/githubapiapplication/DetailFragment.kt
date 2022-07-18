package com.example.githubapiapplication

import android.annotation.TargetApi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapiapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private var webView: WebView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getSerializable("Item") as ItemsGitHub
        webView = binding.myWebView
        webView?.webViewClient = WebViewClient()
        webView?.settings?.javaScriptEnabled = true
        webView?.loadUrl(item.html_url.toString())
    }



}