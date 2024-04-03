package com.spread.xdpbusiness.xdpaddfriend

import com.alibaba.android.arouter.facade.annotation.Route
import com.spread.xdpbusiness.xdpaddfriend.databinding.ActivityAddFriendBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.utils.TestLogger.logd

@Route(path = ArouterUtil.PATH_ACTIVITY_ADD_FRIEND)
class AddFriendActivity : BaseViewBindingActivity<ActivityAddFriendBinding>() {
    override fun getViewBinding(): ActivityAddFriendBinding {
        return ActivityAddFriendBinding.inflate(layoutInflater)
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