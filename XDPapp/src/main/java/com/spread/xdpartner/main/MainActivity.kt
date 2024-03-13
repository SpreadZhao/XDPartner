package com.spread.xdpartner.main


import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xdpartner.databinding.ActivityMainBinding
import com.spread.XDPBusiness.XDPAdd.AddNoteActivity
import com.spread.xdpbusiness.xdpme.MeFragment
import com.spread.xdpbusiness.xdpmessgae.MessageFragment
import com.spread.xdpbusiness.xdpsearch.SearchFragment
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.utils.TestLogger.log
import com.spreadxdpbusiness.xdpaddfriend.FriendFragment

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {
    companion object{
        const val index_home = 0
        const val index_message = 1
        const val index_friend = 2
        const val index_me = 3
        const val index_add = 4
        const val fragment_size = 4
    }
    override fun initView() {
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
        // No sliding
        binding.indexVpFragmentListTop.isUserInputEnabled = false
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
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    inner class TabOnClickListener(var index : Int) : View.OnClickListener {
        override fun onClick(p0: View) {
            log("onclick $index")
            refresh()
            if(index == index_add){
                startAddActivity()
            }else{
                p0.isSelected = true
                binding.indexVpFragmentListTop.setCurrentItem(index,false)
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

