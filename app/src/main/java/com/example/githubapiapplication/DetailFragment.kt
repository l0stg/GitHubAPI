package com.example.githubapiapplication

import android.annotation.TargetApi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.githubapiapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private  var webView: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getSerializable("Item") as ItemsGitHub

        webView = binding?.myWebView
        webView?.webViewClient = WebViewClient()
        // включаем поддержку JavaScript
        webView?.getSettings()?.setJavaScriptEnabled(true)
        // указываем страницу загрузки
        webView?.loadUrl(item.html_url.toString())
    }



}