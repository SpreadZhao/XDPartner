package com.spread.xdpbusiness.xdpmessgae

import com.spread.xdpbusiness.xdpmessgae.databinding.FragmentMessageBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment

class MessageFragment : BaseViewBindingFragment<FragmentMessageBinding>() {
    override fun initView() {

    }

    override fun getViewBinding(): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(layoutInflater)
    }
}