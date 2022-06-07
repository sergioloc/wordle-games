package com.slc.wordlegames.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.slc.wordlegames.databinding.ActivityHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initView()
        initObservers()

        viewModel.getHistory(type)
    }

    private fun initVariables() {
        type = intent.getIntExtra("type", 0)
    }

    private fun initView() {
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
    }

    private fun initObservers() {
        viewModel.history.observe(this) {
            it.onSuccess { list ->
                binding.rvHistory.adapter = HistoryAdapter(list)
            }
        }
    }

}