package com.slc.wordlegames.ui.web

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.slc.wordlegames.R
import com.slc.wordlegames.databinding.ActivityWebViewBinding
import com.slc.wordlegames.domain.model.History
import com.slc.wordlegames.ui.dialog.ConfirmationDialog
import com.slc.wordlegames.ui.dialog.PasteDialog
import com.slc.wordlegames.utils.extensions.simpleFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private val viewModel: WebViewModel by viewModels()
    private var url = ""
    private var type = 0
    private var isComplete = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initView()
        initListeners()
        initObservers()
    }

    override fun onBackPressed() {
        if (isComplete)
            super.onBackPressed()
        else {
            ConfirmationDialog(this).apply {
                setTitle(getString(R.string.did_you_win))
                setMessage(getString(R.string.save_result))
                setOnCloseClickListener {
                    super.onBackPressed()
                }
                setOnAcceptClickListener {
                    showPasteDialog(context, true)
                }
                setOnCancelClickListener {
                    showPasteDialog(context, false)
                }
                show()
            }
        }
    }

    private fun initVariables() {
        type = intent.getIntExtra("type", 0)
        isComplete = intent.getBooleanExtra("isComplete", false)

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

    private fun initObservers() {
        viewModel.saved.observe(this) {
            it.onSuccess {
                super.onBackPressed()
            }
        }
    }

    private fun showPasteDialog(context: Context, success: Boolean) {
        PasteDialog(context).apply {
            if (!success)
                setTitle(getString(R.string.next_time))
            setOnAcceptClickListener {
                viewModel.saveHistory(
                    History(
                        type = type,
                        status = success,
                        result = it,
                        date = Date().simpleFormat()
                    )
                )
            }
            show()
        }
    }
}