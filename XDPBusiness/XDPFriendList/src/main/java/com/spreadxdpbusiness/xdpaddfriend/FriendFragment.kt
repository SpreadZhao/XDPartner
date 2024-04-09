package com.spreadxdpbusiness.xdpaddfriend

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.constant.ArouterUtil
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
        mFriendListAdapter.setClickListener {
            PageUtil.gotoActivityWithUserId(ArouterUtil.PATH_ACTIVITY_PERSON_DETAIL,it)
        }
        binding.titleBar.setRightListener {
            PageUtil.gotoActivity(ArouterUtil.PATH_ACTIVITY_SEARCH_FRIEND)
        }
    }
    private fun searchData(){
        LoginServiceSingle.instance.getAllFriends {
            mFriendListAdapter.setItems(it)
        }
    }
    override fun getViewBinding(): FragmentFriendBinding {
        return FragmentFriendBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        searchData()
    }
}