package com.spread.xdpbusiness.xdpsearch

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.spread.xdpbusiness.xdpsearch.databinding.FragmentSearchBinding
import com.spread.xdplib.adapter.MultiTypeAdapter
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.utils.TestLogger.logd

class SearchFragment : BaseViewBindingFragment<FragmentSearchBinding>() {

    companion object {
        const val newestBlog = 1
        const val likeBlog = 2
        const val hottestBlog = 3
    }

    private val titles by lazy(LazyThreadSafetyMode.NONE) {
        listOf("最新", "猜你喜欢", "最热")
    }
    private val dataSet by lazy(LazyThreadSafetyMode.NONE) {
        listOf(
            MultiTypeData(newestBlog, null),
            MultiTypeData(likeBlog, null),
            MultiTypeData(hottestBlog, null),
        )
    }

    override fun initView() {
        binding.list.adapter = MultiTypeAdapter().apply {
            configDataSet(dataSet)
            addSubAdapter(newestBlog, NewestRecyclerAdapter(requireContext(), this@SearchFragment))
            addSubAdapter(likeBlog, NewestRecyclerAdapter(requireContext(), this@SearchFragment))
            addSubAdapter(hottestBlog, NewestRecyclerAdapter(requireContext(), this@SearchFragment))
        }
        // No sliding
        binding.list.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.list) { tab, position ->
            tab.text = titles[position]
        }.attach()
        // No sliding
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // 第二个参数设置为false以禁用切换动画
                binding.list.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.list.currentItem = 1
        binding.circle.post {
            logd("circle 11x:${binding.circle.pivotX}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PageCurrentDataManager.initAll()
    }

    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }
}