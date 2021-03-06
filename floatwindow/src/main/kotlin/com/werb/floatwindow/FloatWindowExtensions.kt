package com.werb.floatwindow

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by wanbo on 2018/8/1.
 */

/** add float window in android content layout */
fun Activity.addFloatWindow(view: View) {
    val mDecorView = window.decorView as ViewGroup
    val mContentContainer = (mDecorView.getChildAt(0) as ViewGroup).findViewById<FrameLayout>(android.R.id.content)
    val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    mContentContainer.addView(view, layoutParams)
}

internal val Context.widthPixels: Int
    get() {
        val displayMetrics = resources.displayMetrics
        val cf = resources.configuration
        val ori = cf.orientation
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return displayMetrics.heightPixels
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return displayMetrics.widthPixels
        }
        return 0
    }

internal val Context.heightPixels: Int
    get() {
        val displayMetrics = resources.displayMetrics
        val cf = resources.configuration
        val ori = cf.orientation
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return displayMetrics.widthPixels
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return displayMetrics.heightPixels
        }
        return 0
    }

internal fun View.isInViewArea(x: Float, y: Float): Boolean {
    val location = IntArray(2)
    getLocationOnScreen(location)

    val left = location[0]
    val top = location[1]

    val right = left + measuredWidth
    val bottom = top + measuredHeight

    return y >= top && y <= bottom && x >= left && x <= right

}
