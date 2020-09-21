package com.thechefz.chefzlibrary.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


fun Context.appName() =
    applicationInfo.labelRes.let { id ->
        if (id == 0) applicationInfo.nonLocalizedLabel.toString() else getString(id)
    }

fun Context.toBitmap(@DrawableRes resId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, resId) ?: return null
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }

    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}
