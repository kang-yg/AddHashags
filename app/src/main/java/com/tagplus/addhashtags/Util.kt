package com.tagplus.addhashtags

import android.content.Context
import android.graphics.Point
import android.util.TypedValue

object Util {
    fun Context.dpToPx(scale: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scale.toFloat(), resources.displayMetrics).toInt()

    fun Context.pxToDp(scale: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, scale.toFloat(), resources.displayMetrics).toInt()

    fun Context.getDeviceDisplayMetricsPoint(): Point = Point(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)

}