package com.spread.xdpartner.main.fragment

import android.content.Intent
import android.os.Bundle
import com.example.xdpartner.databinding.FragmentMeBinding
import com.spread.xdpartner.LoginActivity
import com.spread.xdplib.adapter.base.BaseViewBindingFragment

class MeFragment : BaseViewBindingFragment<FragmentMeBinding>() {
    override fun getViewBinding(): FragmentMeBinding {
        return FragmentMeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.layoutLogin.setOnClickListener{
            startActivity(Intent(activity,LoginActivity::class.java))
        }
    }

    override fun initView() {

    }
}