package com.geek.android_trainee_task_2021.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geek.android_trainee_task_2021.R
import com.geek.android_trainee_task_2021.common.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior

internal fun View.visibility(isTrue: Boolean) {
    this.visibility = if (isTrue) View.VISIBLE else View.GONE
}

internal fun ImageView.loadImage(url: String) {
    val iconUrl = Constants.icon_base_url + url + Constants.icon_end_point

    Glide.with(this).load(iconUrl).into(this)
}

internal fun ImageView.setImage(isDay: Boolean) {
    val imageId = if (isDay) R.drawable.ic_day
                  else R.drawable.night

    Glide.with(this).load(imageId).into(this)
}

internal fun BottomSheetBehavior<FrameLayout>.show() {
    if (this.state != BottomSheetBehavior.STATE_EXPANDED)
        this.state = BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<FrameLayout>.hide() {
    if (this.state != BottomSheetBehavior.STATE_HIDDEN)
        this.state = BottomSheetBehavior.STATE_HIDDEN
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
