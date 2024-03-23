package com.spread.xdplib.adapter.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.spread.xdplib.adapter.utils.StatusBarUtil


abstract class BaseViewBindingActivity<T : ViewBinding> :AppCompatActivity(){

    lateinit var binding : T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        StatusBarUtil.setImmersionStatusBa(this)
        initView()
    }

    abstract fun getViewBinding(): T
    abstract fun initView()


}