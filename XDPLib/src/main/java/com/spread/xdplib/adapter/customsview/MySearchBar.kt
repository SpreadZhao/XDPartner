package com.spread.xdplib.adapter.customsview

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.spread.xdplib.R
import com.spread.xdplib.databinding.LayoutSearchBarBinding

class MySearchBar : LinearLayout {
    private lateinit var binding:LayoutSearchBarBinding
    private var searchListener: ((text:String?) -> Unit?)? = null
    private var searchText :String? = null
    private val searchWatcher by lazy(LazyThreadSafetyMode.NONE) {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchText = p0.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        }
    }
    var i = 1
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attar: AttributeSet?) : this(context, attar, 0)

    constructor(context: Context, attar: AttributeSet?, def: Int) : super(context, attar, def) {
        init(context, attar)
    }
    private fun init(context: Context, attar: AttributeSet?) {
        binding = LayoutSearchBarBinding.inflate(
            LayoutInflater.from(context),
            this, true
        )
        // 拿到对应的属性名称，记得在最后执行 types.recycle()
        val types = context.obtainStyledAttributes(attar, R.styleable.MySearchBar)
        binding.edit.apply {
            hint = types.getString(R.styleable.MySearchBar_hint)
            addTextChangedListener(searchWatcher)
            currRollingHint = "0"
        }
        types.recycle()
        binding.search.setOnClickListener{
            searchListener?.invoke(searchText)
            binding.edit.rollingTo("${i++}")
        }
    }

    fun setSearchListener(listener: ((text:String?) -> Unit)) {
        this.searchListener = listener
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    fun setSendType(){
        binding.search.setImageDrawable(context.getDrawable(R.drawable.baseline_arrow_forward_24))
        binding.edit.close()
    }
}