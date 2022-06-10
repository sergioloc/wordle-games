package com.slc.wordme.ui.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.animation.Animation
import com.slc.wordme.R
import com.slc.wordme.databinding.DialogBottomBinding

class BottomDialog @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private var binding: DialogBottomBinding
    private var slideOutAnim: Animation

    init {
        binding = DialogBottomBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)

        slideOutAnim = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom)
        slideOutAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                visibility = View.GONE
            }
        })

        binding.ivGradient.setOnClickListener {
            close()
        }
    }

    fun open() {
        visibility = View.VISIBLE
        binding.ivGradient.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
        binding.clFrame.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom))
    }

    private fun close() {
        binding.ivGradient.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
        binding.clFrame.startAnimation(slideOutAnim)
    }

    fun setOnHistoryClickListener(onClickListener: () -> Unit) {
        binding.tvHistory.setOnClickListener {
            onClickListener()
            close()
        }
    }

    fun setOnHideClickListener(onClickListener: () -> Unit) {
        binding.tvHide.setOnClickListener {
            onClickListener()
            close()
        }
    }

}