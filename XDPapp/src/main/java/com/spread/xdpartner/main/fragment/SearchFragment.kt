package com.spread.xdpartner.main.fragment

import com.example.xdpartner.databinding.FragmentSearchBinding
import com.spread.xdpartner.base.BaseViewBindingFragment

class SearchFragment : BaseViewBindingFragment<FragmentSearchBinding>() {
    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }
}