package com.spread.xdpcommon

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.spread.xdpcommon.databinding.LayoutPersonDetailBinding

import com.spread.xdplib.adapter.constant.MapUtil
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class PersonDetailLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : LinearLayout(
    context,
    attrs,
    defStyle
) {
    private var binding: LayoutPersonDetailBinding

    init {
        binding = LayoutPersonDetailBinding.inflate(
            LayoutInflater.from(context),
            this, true
        )
        initLoginView()
    }
    private fun initLoginView(){
        binding.tabLayout.apply {
            addTab(newTab().setText("动态"))
            addTab(newTab().setText("资料"))
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

    fun intList(userId: Long){
        PageCurrentDataManager.initAll()
        val blogListAdapter = BlogListAdapter(context)
            .apply {
                //1、当滑动到列表底部时，查询下一页数据
                setOnFootViewAttachedToWindowListener {
                    //查询数据
                    searchData(userId)
                }
                setHeaderClick(false)
            }
        binding.list.adapter = blogListAdapter
        searchData(userId)
    }
    fun initTextView(userDetail: UserDetail){
        binding.tvMajor.setText("专业：${userDetail.majorName}")
        binding.tvConstellation.setText("星座：${MapUtil.getConstellationName(userDetail.constellation)}")
        binding.tvDemand.setText("需求倾向：${MapUtil.getDemandName(userDetail.highTag)}")
        binding.tvMBTI.setText("MBTI：${MapUtil.getMbtiName(userDetail.mbti)}")
    }
    private fun searchData(userId:Long) {
        LoginServiceSingle.instance.queryOnesBlog(
            PageCurrentDataManager.get(
                PageCurrentDataManager.searchBlogByTypeCurrent
            ), userId
        ) {
            (binding.list.adapter as BlogListAdapter).setData(it)
        }
    }

    private fun changeView(position:Int){
        if(position == 0){
            binding.list.visibility = View.VISIBLE
            binding.layoutDetail.visibility = View.GONE
        } else if(position == 1){
            binding.list.visibility = View.GONE
            binding.layoutDetail.visibility = View.VISIBLE
        }
    }
}