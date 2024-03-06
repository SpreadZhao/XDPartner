package com.spread.xdpartner

import android.os.Bundle
import com.example.xdpartner.databinding.ActivityLoginBinding
import com.spread.xdpartner.base.BaseViewBindingActivity
import com.spread.xdpartner.network.model.LoginBean
import com.spread.xdpartner.network.service.LoginService
import com.spread.xdpartner.network.service.ServiceCreator
import com.spread.xdpartner.test.TestCallBackManager

class LoginActivity : BaseViewBindingActivity<ActivityLoginBinding>() {
    private lateinit var service: LoginService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        service = ServiceCreator.create(LoginService::class.java)
    }

    private fun initView() {
        binding.login.setOnClickListener{
            val loginBean  = LoginBean("20009200612","chr13579shy6",null)
            service.login(loginBean).enqueue(TestCallBackManager.threadsCallback)
        }
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}