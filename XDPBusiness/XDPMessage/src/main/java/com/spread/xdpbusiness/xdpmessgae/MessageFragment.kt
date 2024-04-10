package com.spread.xdpbusiness.xdpmessgae

import com.spread.xdpbusiness.xdpmessgae.databinding.FragmentMessageBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class MessageFragment : BaseViewBindingFragment<FragmentMessageBinding>() {
    override fun initView() {
        searchData()
    }

    override fun getViewBinding(): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(layoutInflater)
    }

    private fun searchData(){
        LoginServiceSingle.instance.connect {

        }
    }
}