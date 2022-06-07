package com.slc.wordlegames.ui.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.slc.wordlegames.R
import com.slc.wordlegames.databinding.ActivityWebViewBinding
import com.slc.wordlegames.ui.dialog.ConfirmationDialog
import com.slc.wordlegames.ui.dialog.PasteDialog
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
            setTitle(getString(R.string.did_you_win))
            setMessage(getString(R.string.save_result))
            setOnAcceptClickListener {
                showPasteDialog(context, true)
            }
            setOnCancelClickListener {
                showPasteDialog(context, false)
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

    private fun showPasteDialog(context: Context, success: Boolean) {
        PasteDialog(context).apply {
            if (!success)
                setTitle(R.string.next_time)
            setOnAcceptClickListener {
                val i = Intent()
                i.putExtra("result", it)
                setResult(RESULT_OK, i)
                super.onBackPressed()
            }
            show()
        }
    }
}