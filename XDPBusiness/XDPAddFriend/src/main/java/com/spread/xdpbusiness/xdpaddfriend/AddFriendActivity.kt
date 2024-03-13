package com.spread.xdpbusiness.xdpaddfriend

import com.spread.xdpbusiness.xdpaddfriend.databinding.ActivityAddFriendBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity


class AddFriendActivity : BaseViewBindingActivity<ActivityAddFriendBinding>() {
    override fun getViewBinding(): ActivityAddFriendBinding {
        return ActivityAddFriendBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.titleBar.setLeftListener {
            finish()
        }
    }
}