package com.slc.wordlegames.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import com.slc.wordlegames.databinding.DialogConfirmationBinding


/** Shows a simple dialog with Accept/Cancel buttons **/

class ConfirmationDialog constructor(val context: Context): CustomDialog() {

    private val binding = DialogConfirmationBinding.inflate(LayoutInflater.from(context))

    /** INIT **/

    init {
        setView(context, binding.root)

        binding.btnCancel.setOnClickListener { dialog.dismiss() }
        binding.ivClose.setOnClickListener { dialog.dismiss() }
    }

    /** SETTERS **/

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setMessage(message: String) {
        binding.tvMessage.text = message
    }

    fun setOnCloseClickListener(onClickListener: () -> Unit) {
        binding.ivClose.setOnClickListener {
            onClickListener()
            dialog.dismiss()
        }
        dialog.setOnCancelListener {
            onClickListener()
        }
    }

    fun setOnAcceptClickListener(onClickListener: () -> Unit) {
        binding.btnAccept.setOnClickListener {
            onClickListener()
            dialog.dismiss()
        }
    }

    fun setOnCancelClickListener(onClickListener: () -> Unit) {
        binding.btnCancel.setOnClickListener {
            onClickListener()
            dialog.dismiss()
        }
    }

}