package com.slc.wordlegames.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.slc.wordlegames.R
import com.slc.wordlegames.databinding.ActivityMenuBinding
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.ui.dialog.ConfirmationDialog
import com.slc.wordlegames.ui.history.HistoryActivity
import com.slc.wordlegames.ui.web.WebActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity(), MenuAdapter.OnGameClickListener {

    private lateinit var binding: ActivityMenuBinding
    private val viewModel: MenuViewModel by viewModels()

    private var closeDialogOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGames()
    }

    override fun onBackPressed() {
        ConfirmationDialog(this).apply {
            setTitle(getString(R.string.warning))
            setMessage(getString(R.string.close_app))
            setOnCloseClickListener {
                closeDialogOpen = false
                dismiss()
            }
            setOnAcceptClickListener {
                super.onBackPressed()
            }
            setOnCancelClickListener {
                closeDialogOpen = false
                dismiss()
            }
            show()
        }
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
        viewModel.hide.observe(this) {
            it.onSuccess {
                viewModel.getGames()
            }
        }
    }

    override fun onClickGame(game: Game) {
        val i = Intent(this, WebActivity::class.java)
        i.putExtra("type", game.id)
        i.putExtra("url", game.url)
        i.putExtra("isComplete", game.status != null)
        startActivity(i)
    }

    override fun onClickOptions(game: Game) {
        binding.bottomDialog.open()
        binding.bottomDialog.setOnHistoryClickListener {
            val i = Intent(this, HistoryActivity::class.java)
            i.putExtra("name", game.name)
            i.putExtra("type", game.id)
            startActivity(i)
        }
        binding.bottomDialog.setOnHideClickListener {
            viewModel.setGameHidden(game.id, false)
        }
    }

}