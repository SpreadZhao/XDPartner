package com.spread.xdpartner.main.fragment

import com.example.xdpartner.databinding.FragmentMessageBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment

class MessageFragment : BaseViewBindingFragment<FragmentMessageBinding>() {
    override fun initView() {

    }

    override fun getViewBinding(): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(layoutInflater)
    }
}