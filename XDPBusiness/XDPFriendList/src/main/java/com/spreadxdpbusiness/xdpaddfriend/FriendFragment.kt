package com.spreadxdpbusiness.xdpaddfriend

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.constant.ArouterPath
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdpnetwork.network.service.LoginServiceSingle
import com.spreadxdpbusiness.friendlist.databinding.FragmentFriendBinding

class FriendFragment : BaseViewBindingFragment<FragmentFriendBinding>() {
    private lateinit var mFriendListAdapter :FriendListAdapter
    override fun initView() {
        mFriendListAdapter = FriendListAdapter(requireContext())
        binding.listFriend.apply {
            layoutManager = LinearLayoutManager(this@FriendFragment.context,
                LinearLayoutManager.VERTICAL,false)
            adapter = mFriendListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@FriendFragment.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        LoginServiceSingle.instance.getAllFriends {
            mFriendListAdapter.setItems(it)
        }

        mFriendListAdapter.setClickListener {

        }
        binding.titleBar.setRightListener {
            PageUtil.gotoActivity(ArouterPath.PATH_ACTIVITY_ADD_FRIEND)
        }
    }

    override fun getViewBinding(): FragmentFriendBinding {
        return FragmentFriendBinding.inflate(layoutInflater)
    }
}