package com.geek.android_trainee_task_2021.extension

import android.app.AlertDialog
import android.content.Context
import com.geek.android_trainee_task_2021.R

fun Context.errorDialog(code: Int?, msg: String?) {
    AlertDialog.Builder(this)
        .setIcon(R.drawable.ic_error_24)
        .setTitle(this.getString(R.string.error_title,code))
        .setMessage(msg)
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }.show()
}