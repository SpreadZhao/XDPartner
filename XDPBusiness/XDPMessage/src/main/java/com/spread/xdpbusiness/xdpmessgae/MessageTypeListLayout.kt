package com.spread.xdpbusiness.xdpmessgae

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.spread.xdpbusiness.xdpmessgae.databinding.LayoutMessageTypeBinding
import com.spread.xdplib.adapter.entry.MessageFiendBean

class MessageTypeListLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : LinearLayout(
    context,
    attrs,
    defStyle
) {
    private var binding: LayoutMessageTypeBinding
    private var mData: List<MessageFiendBean> = emptyList()
    private val adapter =
        MessageListAdapter(context)

    init {
        binding = LayoutMessageTypeBinding.inflate(
            LayoutInflater.from(context),
            this, true
        )
        binding.tabLayout.apply {
            addTab(newTab().setText("熟搭子"))
            addTab(newTab().setText("生搭子"))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    changeView(tab.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    fun setData(data: List<MessageFiendBean>) {
        mData = data
        binding.list.adapter = adapter
        adapter.setData(mData, binding.tabLayout.selectedTabPosition)
    }

    private fun changeView(position: Int) {
        adapter.setData(mData, position)
    }
}