package com.spreadxdpbusiness.xdpaddfriend

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.entry.Friend
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
            //添加好友
            Log.d("FriendFragment","添加好友")
        }
    }

    override fun getViewBinding(): FragmentFriendBinding {
        return FragmentFriendBinding.inflate(layoutInflater)
    }
}