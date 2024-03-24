package com.spread.xdpartner.main.fragment

import android.util.Log
import com.example.xdpartner.databinding.FragmentFriendBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment

class FriendFragment : BaseViewBindingFragment<FragmentFriendBinding>() {
    override fun initView() {
        binding.titleBar.setListener {
            //添加好友
            Log.d("FriendFragment","添加好友")
        }
    }

    override fun getViewBinding(): FragmentFriendBinding {
        return FragmentFriendBinding.inflate(layoutInflater)
    }
}