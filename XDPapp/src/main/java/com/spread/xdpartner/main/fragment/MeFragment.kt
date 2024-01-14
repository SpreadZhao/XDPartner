package com.spread.xdpartner.main.fragment

import com.example.xdpartner.databinding.FragmentMeBinding
import com.spread.xdpartner.base.BaseViewBindingFragment

class MeFragment : BaseViewBindingFragment<FragmentMeBinding>() {
    override fun getViewBinding(): FragmentMeBinding {
        return FragmentMeBinding.inflate(layoutInflater)
    }
}