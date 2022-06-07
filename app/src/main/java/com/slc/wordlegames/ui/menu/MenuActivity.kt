package com.slc.wordlegames.ui.menu

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.slc.wordlegames.databinding.ActivityMenuBinding
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.ui.webview.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity(), MenuAdapter.OnGameClickListener {

    private lateinit var binding: ActivityMenuBinding
    private val viewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()
        viewModel.getGames()
    }

    private fun initView() {
        binding.rvGames.layoutManager = LinearLayoutManager(this)
    }

    private fun initObservers() {
        viewModel.games.observe(this) {
            it.onSuccess { list ->
                binding.rvGames.adapter = MenuAdapter(list, this)
            }
        }
    }

    override fun onClickGame(game: Game) {
        val i = Intent(this, WebViewActivity::class.java)
        i.putExtra("url", game.url)
        activityResult.launch(i)
    }

    override fun onClickOptions(game: Game) {
        Toast.makeText(this, game.name, Toast.LENGTH_SHORT).show()
    }

    var activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val status = it.data?.getBooleanExtra("status", false)
            val result = it.data?.getStringExtra("result")
        }
    }

}