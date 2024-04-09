package com.spreadxdpbusiness.xdpaddfriend

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.utils.TestLogger
import com.spread.xdpnetwork.network.service.LoginServiceSingle
import com.spreadxdpbusiness.friendlist.databinding.ActivityFriendSettingBinding

@Route(path = ArouterUtil.PATH_ACTIVITY_FRIEND_SETTING)
class FriendSettingActivity :BaseViewBindingActivity<ActivityFriendSettingBinding>() {
    @Autowired(name = ArouterUtil.KEY_USERID)
    @JvmField
    var keyUserId: Long = 0
    override fun getViewBinding(): ActivityFriendSettingBinding {
        return ActivityFriendSettingBinding.inflate(layoutInflater)
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        binding.titleBar.setLeftListener {
            finish()
        }
        binding.save.setOnClickListener{
            TestLogger.logd("test:${keyUserId}")
            if(keyUserId.toInt() !=0)
            LoginServiceSingle.instance.changeFriendAlterName(keyUserId.toInt(),binding.desEdit.text.toString()){
                Toast.makeText(this@FriendSettingActivity,it, Toast.LENGTH_SHORT).show()
                if(it == "修改成功"){
                    finish()
                }
            }
        }
    }
}