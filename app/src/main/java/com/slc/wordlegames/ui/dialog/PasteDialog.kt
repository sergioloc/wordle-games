package com.slc.wordlegames.ui.dialog

import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import com.slc.wordlegames.databinding.DialogPasteBinding

/** Shows a dialog to paste game result **/

class PasteDialog constructor(val context: Context): CustomDialog() {

    private val binding = DialogPasteBinding.inflate(LayoutInflater.from(context))

    /** INIT **/

    init {
        setView(context, binding.root)

        binding.ivClose.setOnClickListener { dialog.dismiss() }
        binding.btnPaste.setOnClickListener {
            val clipboard: ClipboardManager? = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            try {
                val textCopied = clipboard?.primaryClip?.getItemAt(0)!!.text
                binding.tvResult.text = textCopied

                if (!textCopied.isNullOrEmpty()) {
                    binding.btnPaste.visibility = View.INVISIBLE
                    binding.btnSave.visibility = View.VISIBLE
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /** SETTERS **/

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setOnAcceptClickListener(onClickListener: (result: String) -> Unit) {
        binding.btnSave.setOnClickListener {
            binding.tvResult.visibility = View.INVISIBLE
            binding.loader.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                onClickListener(binding.tvResult.text.toString())
                dialog.dismiss()
            }, 500)

        }
    }

}