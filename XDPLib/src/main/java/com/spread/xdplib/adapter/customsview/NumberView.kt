package com.spread.xdplib.adapter.customsview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.spread.xdplib.adapter.utils.TestLogger.logd

class NumberView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val numberTextView = TextView(context)
    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }
    private val rectF = RectF()

    init {
        setupView()
    }

    private fun setupView() {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        addView(numberTextView)

        numberTextView.apply {
            layoutParams = LayoutParams(100, 100)
            gravity = Gravity.CENTER
            setTextColor(Color.BLACK)
            background = SolidCircleDrawable()
            textSize = 24f
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(0f, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    fun setNumber(number: Int) {
        if(number == 0) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
            numberTextView.text = number.toString()
        }
    }
}