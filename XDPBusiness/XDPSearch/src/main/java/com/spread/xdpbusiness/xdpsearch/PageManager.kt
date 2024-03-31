package com.spread.xdpbusiness.xdpsearch

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class PageManager(private val context: Context,recyclerView: RecyclerView,currentItem:Int) {
    private val blogListAdapter = BlogListAdapter(context).apply {
        //1、当滑动到列表底部时，查询下一页数据
        setOnFootViewAttachedToWindowListener {
            logd("setOnFootViewAttachedToWindowListener: $currentItem")
            //查询数据
            searchData(currentItem,recyclerView)
        }
    }
    init{
        recyclerView.adapter = blogListAdapter
    }
    fun searchData(position:Int,recyclerView: RecyclerView){
        LoginServiceSingle.instance.queryBlogByPosition(position, PageCurrentDataManager.get(position)) {
            blogListAdapter.setData(it)
        }
    }

}