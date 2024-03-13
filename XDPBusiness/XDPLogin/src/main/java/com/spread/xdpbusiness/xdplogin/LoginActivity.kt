package com.spread.xdpbusiness.xdplogin

import com.spread.xdpbusiness.xdplogin.databinding.ActivityLoginBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdpnetwork.network.model.LoginBean
import com.spread.xdpnetwork.network.service.LoginService
import com.spread.xdpnetwork.network.service.ServiceCreator

class LoginActivity : BaseViewBindingActivity<ActivityLoginBinding>() {
    private lateinit var service: LoginService

    override fun initView() {
        service = ServiceCreator.create(LoginService::class.java)
        binding.login.setOnClickListener{
            val loginBean  = LoginBean("20009200612","chr13579shy6",null)
//            service.login(loginBean).enqueue(TestCallBackManager.threadsCallback)
        }
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}