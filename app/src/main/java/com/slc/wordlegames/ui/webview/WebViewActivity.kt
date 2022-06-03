package com.slc.wordlegames.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.slc.wordlegames.databinding.ActivityWebViewBinding
import com.slc.wordlegames.ui.dialog.ConfirmationDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initView()
        initListeners()
    }

    override fun onBackPressed() {
        ConfirmationDialog(this).apply {
            setOnConfirmationClickListener {
                super.onBackPressed()
            }
            show()
        }
    }

    private fun initVariables() {
        intent.getStringExtra("url")?.let {
            url = it
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.loadUrl(url)
    }

    private fun initListeners() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                binding.loader.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
            }
        }
    }
}