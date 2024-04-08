package com.spread.xdpbusiness.xdpaddfriend

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.spread.xdpbusiness.xdpaddfriend.databinding.ActivityAddFriendBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdpnetwork.network.service.LoginServiceSingle

@Route(path = ArouterUtil.PATH_ACTIVITY_ADD_FRIEND)
class AddFriendActivity :BaseViewBindingActivity<ActivityAddFriendBinding>() {
    @Autowired(name = ArouterUtil.KEY_USER_DETAIL)
    @JvmField
    var userDetail :UserDetail?=null
    @Autowired(name = ArouterUtil.KEY_USERID)
    @JvmField
    var keyUserId: Long = 0
    override fun getViewBinding(): ActivityAddFriendBinding {
        return ActivityAddFriendBinding.inflate(layoutInflater)
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        userDetail?.let {
            binding.name.text = it.nickName
            Glide.with(this).load(it.icon).into(binding.header)
        }
        binding.titleBar.setLeftListener {
            finish()
        }
        binding.send.setOnClickListener{
            LoginServiceSingle.instance.makeFriend(keyUserId.toInt(),binding.desEdit.text.toString()){
                Toast.makeText(this@AddFriendActivity,it,Toast.LENGTH_SHORT).show()
            }
        }
    }

}