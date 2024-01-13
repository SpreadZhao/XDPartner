package com.spread.xdpartner.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xdpartner.databinding.ActivityMainBinding
import com.spread.xdpartner.base.BaseViewBindingActivity
import com.spread.xdpartner.main.fragment.FriendFragment
import com.spread.xdpartner.main.fragment.MeFragment
import com.spread.xdpartner.main.fragment.MessageFragment
import com.spread.xdpartner.main.fragment.SearchFragment
import com.spread.xdpartner.test.TestLogger.log

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.indexVpFragmentListTop.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> SearchFragment()
                    1 -> MessageFragment()
                    2 -> FriendFragment()
                    3 -> MeFragment()
                    else -> SearchFragment()
                }
            }

            override fun getItemCount() = 4
        }
        binding.indexVpFragmentListTop.currentItem = 0
        binding.indexBottomBarHome.isSelected = true
        binding.indexBottomBarHome.setOnClickListener(TabOnClickListener(0))
        binding.indexBottomBarMessage.setOnClickListener(TabOnClickListener(1))
        binding.indexBottomBarFriend.setOnClickListener(TabOnClickListener(2))
        binding.indexBottomMe.setOnClickListener(TabOnClickListener(3))
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    inner class TabOnClickListener(var index : Int) : View.OnClickListener {
        override fun onClick(p0: View) {
            log("onclick $index")
            refreach()
            p0.isSelected = true
            binding.indexVpFragmentListTop.setCurrentItem(index)

        }

        private fun refreach() {
            binding.indexBottomBarHome.isSelected = false
            binding.indexBottomBarFriend.isSelected = false
            binding.indexBottomBarMessage.isSelected = false
            binding.indexBottomMe.isSelected = false
        }

    }
}

