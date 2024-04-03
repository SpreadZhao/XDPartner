package com.spread.xdplib.adapter.customsview

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import androidx.core.animation.addListener

class RollingEditText : androidx.appcompat.widget.AppCompatEditText {

    var currRollingHint = ""
        set(value) {
            field = value
            invalidate()
        }
    private val mPaint = Paint()

    private var useCurrBounds = false
    private var currBounds = getLineBounds(0, null).toFloat()

    constructor(context: Context) : this(context, null)
    // 注意android.R.attr.editTextStyle，不能写成0
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.editTextStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        mPaint.apply {
            isAntiAlias = true
            color = this@RollingEditText.hintTextColors.defaultColor
            textSize = this@RollingEditText.textSize
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!TextUtils.isEmpty(text) || !TextUtils.isEmpty(hint)) {
            return
        }
        val startX = compoundPaddingLeft.toFloat()
        canvas.drawText(
            currRollingHint,
            startX,
            if (useCurrBounds) currBounds else getLineBounds(0, null).toFloat(),
            mPaint
        )
    }

    @SuppressLint("ResourceType")
    fun rollingTo(newHint: String) {
        val baseBounds = getLineBounds(0, null).toFloat()
        val lenHeight = measuredHeight
        val animStart = ValueAnimator.ofFloat(baseBounds, baseBounds - lenHeight)
        animStart.addUpdateListener {
            currBounds = it.animatedValue as? Float ?: return@addUpdateListener
            invalidate()
        }
        animStart.addListener(
            onStart = { useCurrBounds = true },
            onEnd = {
                currRollingHint = newHint
                val animEnd = ValueAnimator.ofFloat(baseBounds + lenHeight, baseBounds)
                animEnd.addUpdateListener { animator ->
                    currBounds = animator.animatedValue as? Float ?: return@addUpdateListener
                    invalidate()
                }
                animEnd.addListener(
                    onEnd = { useCurrBounds = true }
                )
                animEnd.duration = 200
                animEnd.start()
            }
        )
        animStart.duration = 200
        animStart.start()
    }

}