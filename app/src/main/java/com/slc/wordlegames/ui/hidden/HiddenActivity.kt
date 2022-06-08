package com.slc.wordlegames.ui.hidden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.slc.wordlegames.R
import com.slc.wordlegames.databinding.ActivityHiddenBinding
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.ui.dialog.ConfirmationDialog
import com.slc.wordlegames.ui.menu.MenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiddenActivity : AppCompatActivity(), MenuAdapter.OnGameClickListener {

    private lateinit var binding: ActivityHiddenBinding
    private val viewModel: HiddenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiddenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()

        viewModel.getHiddenGames()
    }

    private fun initView() {
        binding.rvHidden.layoutManager = LinearLayoutManager(this)
    }

    private fun initObservers() {
        viewModel.games.observe(this) {
            it.onSuccess { list ->
                binding.rvHidden.adapter = MenuAdapter(list, this, false)
            }
        }
        viewModel.hide.observe(this) {
            it.onSuccess {
                viewModel.getHiddenGames()
            }
        }
    }

    override fun onClickGame(game: Game) {
        ConfirmationDialog(this).apply {
            setTitle(game.name)
            setMessage(getString(R.string.show_game))
            setOnAcceptClickListener {
                viewModel.setGameHidden(game.id, true)
            }
            show()
        }
    }

    override fun onClickOptions(game: Game) {

    }

}