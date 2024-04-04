package com.spread.xdpbusiness.xdpsearch

import androidx.recyclerview.widget.RecyclerView
import com.spread.xdpcommon.BlogListAdapter
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdpnetwork.network.service.LoginServiceSingle

@Deprecated("please use viewPagerAdapter")
object PageManager {
    fun searchData(position:Int,recyclerView: RecyclerView){
        LoginServiceSingle.instance.queryBlogByPosition(position, PageCurrentDataManager.get(position)) {
            (recyclerView.adapter as BlogListAdapter).setData(it)
        }
    }

}