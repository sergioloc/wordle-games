package com.slc.wordlegames.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.slc.wordlegames.R
import com.slc.wordlegames.ui.webview.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private val viewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //startActivity(Intent(this, WebViewActivity::class.java))

        initObservers()
        viewModel.getGames()
    }

    private fun initObservers() {
        viewModel.games.observe(this) {
            it.onSuccess { list ->
                Log.i("TAGTAG", list.size.toString())
            }
        }
    }
}