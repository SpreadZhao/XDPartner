package com.spread.xdpbusiness.xdpmessgae

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.spread.xdpbusiness.xdpmessgae.databinding.ActivityImBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.entry.UserVo

@Route(path = ArouterUtil.PATH_ACTIVITY_MESSAGE_IM)
class IMActivity : BaseViewBindingActivity<ActivityImBinding>() {

    @Autowired(name = ArouterUtil.KEY_USER_VO)
    @JvmField
    var keyUserVo: UserVo = UserVo("",0,"")

    override fun getViewBinding(): ActivityImBinding {
        return ActivityImBinding.inflate(layoutInflater)
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        binding.titleBar.apply {
            setTitleText(keyUserVo.nickName)
            setLeftListener {
                finish()
            }
        }
        binding.send.setSendType()
        binding.send.setSearchListener {

        }
    }


}