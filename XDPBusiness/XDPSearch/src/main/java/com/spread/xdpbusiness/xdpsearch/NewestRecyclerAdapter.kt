package com.spread.xdpbusiness.xdpsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.SubAdapterBase
import com.spread.xdplib.adapter.utils.TestLogger.log
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class NewestRecyclerAdapter(
    private val context: Context
) : SubAdapterBase() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        // 创建RecyclerView实例

        val recyclerView = RecyclerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

        }
        return RecycleViewHolder(recyclerView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        data: MultiTypeData,
        position: Int
    ) {
        if (holder is RecycleViewHolder) {
            val adapter = BlogListAdapter(context)
            holder.recyclerView.adapter = adapter
            LoginServiceSingle.instance.queryBlogByPosition(position,1) {
                log("queryHottestBlog: $it")
                adapter.setData(it)
            }
        }
    }

}

