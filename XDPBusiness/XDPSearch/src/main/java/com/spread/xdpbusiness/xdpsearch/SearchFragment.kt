package com.spread.xdpbusiness.xdpsearch

import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.spread.xdpbusiness.xdpsearch.databinding.FragmentSearchBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.service.LoginServiceSingle


class SearchFragment : BaseViewBindingFragment<FragmentSearchBinding>() {

    companion object {
        @JvmStatic
        val newestBlog = 1
        @JvmStatic
        val likeBlog = 2
        @JvmStatic
        val hottestBlog = 3
    }

    private val titles by lazy(LazyThreadSafetyMode.NONE) {
        listOf("最新", "猜你喜欢", "最热")
    }
    private val viewPagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ViewPagerAdapter(requireContext(),this@SearchFragment)
    }
    override fun initView() {
        searchData()
        binding.list.adapter = viewPagerAdapter
        // No sliding
        binding.list.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.list) { tab, position ->
            tab.text = titles[position]
        }.attach()
        // No sliding
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                PageCurrentDataManager.initAll()
                // 第二个参数设置为false以禁用切换动画
                binding.list.setCurrentItem(tab.position, false)
                setSelectedTabChoose(true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {
                PageCurrentDataManager.initAll()
                viewPagerAdapter.notifyItemRangeChanged(tab.position,1)
                setSelectedTabChoose(true)
            }
        })
        binding.list.currentItem = 1
        binding.searchBar.setSearchListener {
            if(it.isNullOrEmpty()) return@setSearchListener
            viewPagerAdapter.updateBlogList(it,binding.list.currentItem)
            setSelectedTabChoose(false)
        }
        binding.cvStudy.setOnClickListener{
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE,1)
        }
        binding.cvHappy.setOnClickListener{
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE,2)
        }
        binding.cvLove.setOnClickListener{
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE,3)
        }
        binding.cvLife.setOnClickListener{
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE,4)
        }
    }

    private fun setSelectedTabChoose(isChoose:Boolean){
        if(isChoose){
            binding.tabLayout.setTabTextColors(requireContext().getColor(com.spread.xdplib.R.color.black),requireContext().getColor(
                com.spread.xdplib.R.color.purple))
            binding.tabLayout.setSelectedTabIndicatorColor(requireContext().getColor(com.spread.xdplib.R.color.purple))
        }else {
            binding.tabLayout.setTabTextColors(requireContext().getColor(com.spread.xdplib.R.color.black),requireContext().getColor(
                com.spread.xdplib.R.color.black))
            binding.tabLayout.setSelectedTabIndicatorColor(requireContext().getColor(com.spread.xdplib.R.color.transparency))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        PageCurrentDataManager.initAll()
    }

    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    private fun searchData(){
        LoginServiceSingle.instance.queryOther(NetworkConstant.userId){
            binding.tvName.text = "${it.nickName}，你好"
            Glide.with(requireContext()).load(it.icon).into(binding.circle)
        }
    }
}