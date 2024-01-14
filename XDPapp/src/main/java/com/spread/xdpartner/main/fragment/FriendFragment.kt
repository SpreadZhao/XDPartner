package com.spread.xdpartner.main.fragment

import com.example.xdpartner.databinding.FragmentFriendBinding
import com.spread.xdpartner.base.BaseViewBindingFragment

class FriendFragment : BaseViewBindingFragment<FragmentFriendBinding>() {
    override fun getViewBinding(): FragmentFriendBinding {
        return FragmentFriendBinding.inflate(layoutInflater)
    }
}