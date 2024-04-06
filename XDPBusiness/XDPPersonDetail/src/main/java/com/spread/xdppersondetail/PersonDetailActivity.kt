package com.spread.xdppersondetail

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdpnetwork.network.service.LoginServiceSingle
import com.spread.xdppersondetail.databinding.ActivityPersonDetailBinding

@Route(path = ArouterUtil.PATH_ACTIVITY_PERSON_DETAIL)
class PersonDetailActivity : BaseViewBindingActivity<ActivityPersonDetailBinding>() {
    @Autowired(name = "keyUserId")
    @JvmField
    var keyUserId: Long = 0
    override fun getViewBinding(): ActivityPersonDetailBinding {
        return ActivityPersonDetailBinding.inflate(layoutInflater)
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        binding.titleBar.setLeftListener {
            finish()
        }
        binding.message.setOnClickListener{

        }
        binding.add.setOnClickListener{

        }
        searchDetail()
    }
    private fun searchDetail(){
        LoginServiceSingle.instance.queryOther(keyUserId){
            binding.layoutPerson.intList(keyUserId)
            binding.layoutPerson.initTextView(it)
            binding.description.text = "个人简介：${it.myDescription}"
            binding.name.text = it.nickName
            Glide.with(this).load(it.icon).into(binding.header)
        }
    }
}