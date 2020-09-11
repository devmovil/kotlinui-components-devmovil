package com.rnnzzo.uxdesign.util

import android.graphics.PorterDuff
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.rnnzzo.uxdesign.R


fun LinearLayout.addProgressDots(current_index: Int, maxStep: Int) {
    val dots = arrayOfNulls<ImageView>(maxStep)
    this.removeAllViews()
    for (i in dots.indices) {
        dots[i] = ImageView(context)
        val width_height = 15
        val params =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
        params.setMargins(10, 10, 10, 10)
        dots[i]!!.layoutParams = params
        dots[i]!!.setImageResource(R.drawable.circle)
        dots[i]!!.setColorFilter(getResources().getColor(R.color.grey_300), PorterDuff.Mode.SRC_IN)
        this.addView(dots[i])
    }
    if (dots.size > 0) {
        dots[current_index]!!.setImageResource(R.drawable.circle)
        dots[current_index]!!.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN)
    }
}