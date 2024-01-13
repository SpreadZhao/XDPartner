package com.spread.xdpartner.main.fragment

import com.example.xdpartner.databinding.FragmentMessageBinding
import com.spread.xdpartner.base.BaseViewBindingFragment

class MessageFragment : BaseViewBindingFragment<FragmentMessageBinding>() {
    override fun getViewBinding(): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(layoutInflater)
    }
}