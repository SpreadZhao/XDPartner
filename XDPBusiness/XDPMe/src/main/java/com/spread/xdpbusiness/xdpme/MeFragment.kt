package com.spread.xdpbusiness.xdpme

import com.spread.xdpbusiness.xdpme.databinding.FragmentMeBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment

class MeFragment : BaseViewBindingFragment<FragmentMeBinding>() {
    override fun getViewBinding(): FragmentMeBinding {
        return FragmentMeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.layoutLogin.setOnClickListener{

        }
    }
}