package com.spread.xdplib.adapter.customsview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable

class SolidCircleDrawable : Drawable() {
    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        val centerX = bounds.exactCenterX()
        val centerY = bounds.exactCenterY()
        val radius = bounds.width().coerceAtMost(bounds.height()) / 2f

        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}