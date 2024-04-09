package com.spread.xdpbusiness.xdpaddfriend

import com.alibaba.android.arouter.facade.annotation.Route
import com.spread.xdpbusiness.xdpaddfriend.databinding.ActivitySearchFriendBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.utils.TestLogger.logd

@Route(path = ArouterUtil.PATH_ACTIVITY_SEARCH_FRIEND)
class SearchFriendActivity : BaseViewBindingActivity<ActivitySearchFriendBinding>() {
    override fun getViewBinding(): ActivitySearchFriendBinding {
        return ActivitySearchFriendBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.titleBar.setLeftListener {
            finish()
        }
        binding.searchBar.setSearchListener {
            if (it != null) {
                logd(it)
            }
        }
    }
}