package com.slc.wordlegames.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.slc.wordlegames.databinding.ActivitySettingsBinding
import com.slc.wordlegames.ui.HiddenActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtons()
    }

    private fun initButtons() {
        binding.tvHidden.setOnClickListener {
            startActivity(Intent(this, HiddenActivity::class.java))
        }
    }
}