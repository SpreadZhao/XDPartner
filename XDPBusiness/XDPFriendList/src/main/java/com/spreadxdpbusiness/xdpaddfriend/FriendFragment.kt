package com.spreadxdpbusiness.xdpaddfriend

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.constant.ArouterPath
import com.spread.xdplib.adapter.entry.Friend
import com.spread.xdplib.adapter.utils.PageUtil
import com.spreadxdpbusiness.friendlist.databinding.FragmentFriendBinding

class FriendFragment : BaseViewBindingFragment<FragmentFriendBinding>() {
    private val mFriendListAdapter :FriendListAdapter = FriendListAdapter()
    override fun initView() {
        binding.listFriend.apply {
            layoutManager = LinearLayoutManager(this@FriendFragment.context,LinearLayoutManager.VERTICAL,false)
            adapter = mFriendListAdapter 
            addItemDecoration(
                DividerItemDecoration(
                    this@FriendFragment.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        val friends = listOf<Friend>(Friend(1,"1","123"),Friend(1,"1","123"),Friend(1,"1","123"))
        mFriendListAdapter.setItems(friends)
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