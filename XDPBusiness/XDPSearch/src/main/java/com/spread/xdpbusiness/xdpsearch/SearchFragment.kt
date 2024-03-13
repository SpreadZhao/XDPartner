package com.spread.xdpbusiness.xdpsearch

import com.spread.xdpbusiness.xdpsearch.databinding.FragmentSearchBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment

class SearchFragment : BaseViewBindingFragment<FragmentSearchBinding>() {
    override fun initView() {

    }

    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }
}