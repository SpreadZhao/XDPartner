package com.spread.xdplib.adapter.customsview.hotwords

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


class HotWordsLayout : FrameLayout {

    private var words: Map<String, Int>? = null
    private var measuredWidth = 0
    private var measuredHeight = 0
    private var currDeepest = 0
    private var currLeftest = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        this.measuredWidth = getMeasuredWidth()
        this.measuredHeight = getMeasuredHeight()
        this.currDeepest = measuredHeight
    }

    fun initialize(words: Map<String, Int>): Boolean {
        if (words.isEmpty()) {
            return false
        }
        this.words = words.toList().sortedBy { (_, value) -> value }.toMap()
        return true
    }

    fun something() {
        if (words == null) {
            return
        }
        val words: Map<String, Int> = this.words!!
        val radius = 100
        val centers = ArrayList<Int>(words.size)
        for ((word, populates) in words) {

        }
    }


}