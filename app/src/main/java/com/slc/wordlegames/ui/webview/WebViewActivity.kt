package com.slc.wordlegames.ui.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.slc.wordlegames.databinding.ActivityWebViewBinding
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
    }

    private fun initVariables() {
        intent.getStringExtra("url")?.let {
            url = it
        }
    }

    private fun initView() {
        binding.webView.loadUrl(url)
    }
}