package com.spread.xdplib.adapter.customsview

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.spread.xdplib.R
import com.spread.xdplib.databinding.LayoutTitlebarBinding

class TitleBar : LinearLayout {
    private lateinit var binding: LayoutTitlebarBinding
    private var rightListener: (() -> Unit)? = null
    private var leftListener: (() -> Unit)? = null
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attar: AttributeSet?) : this(context, attar, 0)

    constructor(context: Context, attar: AttributeSet?, def: Int) : super(context, attar, def) {
        init(context, attar)
    }

    private fun init(context: Context, attar: AttributeSet?) {
        // 拿到我们编写的布局文件，这部分就相当于固定的部分
        binding = LayoutTitlebarBinding.inflate(
            LayoutInflater.from(context),
            this, true
        )
        // 拿到对应的属性名称，记得在最后执行 types.recycle()
        val types = context.obtainStyledAttributes(attar, R.styleable.TitleBar)
        // 根据 xml 中设置的值设置
        binding.tvTitle.text = types.getString(R.styleable.TitleBar_title)
        if (types.getBoolean(R.styleable.TitleBar_add_visible, false)) {
            binding.icAdd.apply {
                this.visibility = View.VISIBLE
                this.setOnClickListener { rightListener?.invoke() }
            }
        } else
            binding.icAdd.visibility = View.INVISIBLE
        val color = types.getColor(
            R.styleable.TitleBar_back_color,
            ContextCompat.getColor(context, R.color.purple)
        )
        binding.icBack.apply {
            setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            setOnClickListener {
                leftListener?.invoke()
            }
        }

        types.recycle()

    }

    fun setRightListener(listener: (() -> Unit)) {
        this.rightListener = listener
    }

    fun setLeftListener(listener: (() -> Unit)) {
        this.leftListener = listener
    }

    fun setTitleText(title:String){
        binding.tvTitle.text = title
    }

    fun setLeftVisible(vis:Int){
        binding.icBack.visibility = vis
    }
}