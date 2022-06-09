package com.slc.wordlegames.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.slc.wordlegames.R
import com.slc.wordlegames.databinding.ActivitySettingsBinding
import com.slc.wordlegames.ui.hidden.HiddenActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_bottom_slow, R.anim.idle)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initButtons()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.idle, R.anim.slide_out_bottom_slow)
    }

    private fun initView() {
        binding.adBanner.loadAd(AdRequest.Builder().build())
    }

    private fun initButtons() {
        binding.tvHidden.setOnClickListener {
            startActivity(Intent(this, HiddenActivity::class.java))
        }
    }
}