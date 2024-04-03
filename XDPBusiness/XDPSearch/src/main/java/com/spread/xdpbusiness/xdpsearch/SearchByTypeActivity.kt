package com.spread.xdpbusiness.xdpsearch

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.spread.xdpbusiness.xdpsearch.databinding.ActivitySearchTypeBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdpnetwork.network.service.LoginServiceSingle


@Route(path = ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE)
class SearchByTypeActivity : BaseViewBindingActivity<ActivitySearchTypeBinding>() {
    @Autowired(name = "searchType")
    @JvmField
    var searchType :Int = 0
    override fun getViewBinding(): ActivitySearchTypeBinding {
        return ActivitySearchTypeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        setTitle()
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        binding.searchBar.setSearchListener {
            PageCurrentDataManager.initAll()
            if(it.isNullOrEmpty()) return@setSearchListener
            val blogListAdapter = BlogListAdapter(this@SearchByTypeActivity).apply {
                //1、当滑动到列表底部时，查询下一页数据
                setOnFootViewAttachedToWindowListener {
                    //查询数据
                    searchData(it)
                }
            }
            binding.list.adapter = blogListAdapter
            searchData( it)
        }
    }
    private fun searchData(msg:String){
        LoginServiceSingle.instance.searchTagWordByTypeId(PageCurrentDataManager.get(PageCurrentDataManager.searchBlogByTypeCurrent),searchType,msg){
            (binding.list.adapter as BlogListAdapter).setData(it)
        }
    }
    private fun setTitle() {
        val title = when (searchType) {
            1 -> "学习"
            2 -> "娱乐"
            3 -> "恋爱"
            4 -> "生活"
            else -> { "学习" }
        }
        binding.title.setTitleText(title)
    }

    override fun onStop() {
        super.onStop()
        PageCurrentDataManager.initAll()
    }
}