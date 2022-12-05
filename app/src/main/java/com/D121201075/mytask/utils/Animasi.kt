package com.D121201075.mytask.utils

import android.app.Activity
import android.content.Context
import com.D121201075.mytask.R

object Animasi {
    fun animateSlideLeft(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.animate_slide_left_enter,
            R.anim.animate_slide_left_exit
        )
    }

    fun animateSlideRight(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.animate_slide_in_left,
            R.anim.animate_slide_out_right
        )
    }
}