package com.spread.xdpbusiness.xdplogin

import android.text.Editable
import android.text.TextWatcher
import com.alibaba.android.arouter.facade.annotation.Route
import com.spread.xdpbusiness.xdplogin.databinding.ActivityLoginBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterPath
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdpnetwork.network.TestCallBackManager
import com.spread.xdpnetwork.network.model.LoginBean
import com.spread.xdpnetwork.network.service.LoginService
import com.spread.xdpnetwork.network.service.ServiceCreator

@Route(path = ArouterPath.PATH_ACTIVITY_LOGIN)
class LoginActivity : BaseViewBindingActivity<ActivityLoginBinding>() {
    private lateinit var service: LoginService
    private var textAccount :String = ""
    private var textPW :String = ""
    private val accountWatcher by lazy(LazyThreadSafetyMode.NONE) {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textAccount = p0.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        }
    }
    private val pwWatcher by lazy(LazyThreadSafetyMode.NONE) {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textPW = p0.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        }
    }
    override fun initView() {
        binding.back.setOnClickListener{
            PageUtil.gotoActivityIfExist(this@LoginActivity,ArouterPath.PATH_ACTIVITY_MAIN)
        }
        service = ServiceCreator.create(LoginService::class.java)
        binding.editAccount.addTextChangedListener(accountWatcher)
        binding.editPW.addTextChangedListener(pwWatcher)
        binding.login.setOnClickListener{
            val loginBean  = LoginBean("12345678","12345678","0")
            service.login(loginBean).enqueue(TestCallBackManager.threadsCallback)
        }
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

}