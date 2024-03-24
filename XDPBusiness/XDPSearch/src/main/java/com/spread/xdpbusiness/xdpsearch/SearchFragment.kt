package com.spread.xdpbusiness.xdpsearch

import com.spread.xdpbusiness.xdpsearch.databinding.FragmentSearchBinding
import com.spread.xdplib.adapter.MultiTypeAdapter
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.utils.TestLogger.log

class SearchFragment : BaseViewBindingFragment<FragmentSearchBinding>() {

    companion object{
        const val newestBlog = 1
        const val likeBlog = 2
        const val hottestBlog = 3
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
            addSubAdapter(newestBlog,NewestRecyclerAdapter(requireContext()))
            addSubAdapter(likeBlog,NewestRecyclerAdapter(requireContext()))
            addSubAdapter(hottestBlog,NewestRecyclerAdapter(requireContext()))
        }
        // No sliding
        binding.list.isUserInputEnabled = false
        binding.list.currentItem = 1
        binding.tvNew.setOnClickListener{
            binding.list.currentItem = 0
            log("currentItem ${binding.list.currentItem}")
        }
        binding.tvLike.setOnClickListener{
            binding.list.currentItem = 1
            log("currentItem ${binding.list.currentItem}")
        }
        binding.tvHot.setOnClickListener{
            binding.list.currentItem = 2
            log("currentItem ${binding.list.currentItem}")
        }
    }

    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }
}