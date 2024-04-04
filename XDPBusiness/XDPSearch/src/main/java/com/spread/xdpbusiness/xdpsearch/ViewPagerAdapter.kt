package com.spread.xdpbusiness.xdpsearch

import android.content.Context
import android.util.SparseArray
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager.searchBlogCurrent
import com.spread.xdpbusiness.xdpsearch.SearchFragment.Companion.hottestBlog
import com.spread.xdpbusiness.xdpsearch.SearchFragment.Companion.likeBlog
import com.spread.xdpbusiness.xdpsearch.SearchFragment.Companion.newestBlog
import com.spread.xdpcommon.BlogListAdapter
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class ViewPagerAdapter(private val context: Context, private val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<RecycleViewHolder>(),
    LifecycleEventObserver {
    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }
    private var recyclerViewList:SparseArray<RecyclerView> = SparseArray()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
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
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        GlideUtil.isResumeRequests(context, true)
//                    } else {
//                        GlideUtil.isResumeRequests(context, false)
//                    }
//                }
//            })
        }
        return RecycleViewHolder(recyclerView)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val blogListAdapter = BlogListAdapter(context).apply {
            //1、当滑动到列表底部时，查询下一页数据
            setOnFootViewAttachedToWindowListener {
                //查询数据
                searchData(position, holder.recyclerView)
            }
        }
        holder.recyclerView.adapter = blogListAdapter
        recyclerViewList.put(position,holder.recyclerView)
        searchData(position, holder.recyclerView)
    }

    private fun searchData(position: Int, recyclerView: RecyclerView) {
        LoginServiceSingle.instance.queryBlogByPosition(
            position,
            PageCurrentDataManager.get(position)
        ) {
            (recyclerView.adapter as BlogListAdapter).setData(it)
        }
    }
    private fun searchDataByMsg(position: Int,msg:String, recyclerView: RecyclerView) {
        LoginServiceSingle.instance.searchBlog(
            PageCurrentDataManager.get(position), msg
        ) {
            (recyclerView.adapter as BlogListAdapter).setData(it)
        }
    }
    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return newestBlog
        } else if (position == 1) {
            return likeBlog
        } else if (position == 2) {
            return hottestBlog
        }
        return super.getItemViewType(position)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//        when (event) {
//            Lifecycle.Event.ON_RESUME -> GlideUtil.isResumeRequests(context, true)
//            Lifecycle.Event.ON_PAUSE -> GlideUtil.isResumeRequests(context, false)
//            else -> {}
//        }
    }

    fun updateBlogList(msg: String,position: Int){
        val recyclerView = recyclerViewList.get(position)
        val blogListAdapter = BlogListAdapter(context).apply {
            //1、当滑动到列表底部时，查询下一页数据
            setOnFootViewAttachedToWindowListener {
                //查询数据
                searchDataByMsg(searchBlogCurrent, msg,recyclerView)
            }
        }
        recyclerView.adapter = blogListAdapter
        searchDataByMsg(searchBlogCurrent, msg,recyclerView)
    }
}