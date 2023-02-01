package com.ayodev.store_challenge.util.custom_view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.ayodev.store_challenge.R

class AppLoadingDialog(context: Context) {

    private var progress: AlertDialog

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.dialog_loading, null)

        progress = AlertDialog.Builder(context)
            .setView(layout)
            .setCancelable(false)
            .create()
    }

    fun show() {
        progress.show()
    }

    fun dismiss() {
        progress.dismiss()
    }

}