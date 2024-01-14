package com.spread.xdpartner.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xdpartner.databinding.ActivityMainBinding
import com.spread.xdpartner.add.AddActivity
import com.spread.xdpartner.base.BaseViewBindingActivity
import com.spread.xdpartner.main.fragment.FriendFragment
import com.spread.xdpartner.main.fragment.MeFragment
import com.spread.xdpartner.main.fragment.MessageFragment
import com.spread.xdpartner.main.fragment.SearchFragment
import com.spread.xdpartner.test.TestLogger.log
const val index_home = 0
const val index_message = 1
const val index_friend = 2
const val index_me = 3
const val index_add = 4
const val fragment_size = 4
class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.indexVpFragmentListTop.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    index_home -> SearchFragment()
                    index_message -> MessageFragment()
                    index_friend -> FriendFragment()
                    index_me -> MeFragment()
                    else -> SearchFragment()
                }
            }

            override fun getItemCount() = fragment_size
        }
        binding.indexVpFragmentListTop.currentItem = index_home
        binding.indexBottomBarHome.isSelected = true
        binding.indexBottomBarHome.setOnClickListener(TabOnClickListener(index_home))
        binding.indexBottomBarMessage.setOnClickListener(TabOnClickListener(index_message))
        binding.indexBottomBarFriend.setOnClickListener(TabOnClickListener(index_friend))
        binding.indexBottomMe.setOnClickListener(TabOnClickListener(index_me))
        binding.indexBottomBarScan.setOnClickListener(TabOnClickListener(index_add))
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun startAddActivity(){
        startActivity(Intent(this,AddActivity::class.java))
    }
    inner class TabOnClickListener(var index : Int) : View.OnClickListener {
        override fun onClick(p0: View) {
            log("onclick $index")
            refresh()
            if(index == index_add){
                startAddActivity()
            }else{
                p0.isSelected = true
                binding.indexVpFragmentListTop.setCurrentItem(index)
            }

        }

        private fun refresh() {
            binding.indexBottomBarHome.isSelected = false
            binding.indexBottomBarFriend.isSelected = false
            binding.indexBottomBarMessage.isSelected = false
            binding.indexBottomMe.isSelected = false
        }

    }
}

