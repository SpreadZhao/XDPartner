package com.spread.xdpbusiness.xdpsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.SubAdapterBase
import com.spread.xdplib.adapter.utils.GlideUtil

class NewestRecyclerAdapter(
    private val context: Context, private val lifecycleOwner: LifecycleOwner
) : SubAdapterBase(), LifecycleEventObserver {
    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        // 创建RecyclerView实例
        val recyclerView = RecyclerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        GlideUtil.isResumeRequests(context, true)
                    } else {
                        GlideUtil.isResumeRequests(context, false)
                    }
                }
            })
        }
        return RecycleViewHolder(recyclerView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        data: MultiTypeData,
        position: Int
    ) {
        if (holder is RecycleViewHolder) {
            PageManager(context, holder.recyclerView, position).apply {
                searchData(position, holder.recyclerView)
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> GlideUtil.isResumeRequests(context, true)
            Lifecycle.Event.ON_PAUSE -> GlideUtil.isResumeRequests(context, false)
            else -> {}
        }
    }
}

