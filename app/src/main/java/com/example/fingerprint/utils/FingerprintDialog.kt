package com.example.fingerprint.utils

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.fingerprint.R
import com.example.fingerprint.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class FingerprintDialog  : BottomSheetDialog, View.OnClickListener {

    @get:JvmName("getContext_")
    var context: Context? = null
    lateinit var binding: BottomSheetBinding
    private var fingerprintCallback: FingerprintCallback? = null

    constructor(context: Context) : super(context, R.style.BottomSheetDialogTheme) {
        this.context = context.applicationContext
        setDialogView()
    }

    constructor(context: Context, fingerprintCallback: FingerprintCallback?) : super(
        context,
        R.style.BottomSheetDialogTheme
    ) {
        this.context = context.applicationContext
        this.fingerprintCallback = fingerprintCallback
        setDialogView()
    }

    constructor(context: Context, theme: Int) : super(context, theme)
    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    private fun setDialogView() {
        binding = BottomSheetBinding.inflate(layoutInflater)

       // val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        setContentView(binding.root)
        binding.btnCancel.setOnClickListener(this)
        updateLogo()
    }

    fun setTitle(title: String?) {
        binding.itemTitle.text = title
    }

    fun updateStatus(status: String?) {
        binding.itemStatus.text = status
    }

    fun setSubtitle(subtitle: String?) {
        binding.itemSubtitle.text = subtitle
    }

    fun setDescription(description: String?) {
        itemDescription!!.text = description
    }

    fun setButtonText(negativeButtonText: String?) {
        btnCancel!!.text = negativeButtonText
    }

    private fun updateLogo() {
        try {
            val drawable = getContext().packageManager.getApplicationIcon(context!!.packageName)
            imgLogo!!.setImageDrawable(drawable)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(view: View) {
        dismiss()
        fingerprintCallback!!.onAuthenticationCancelled()
    }
}