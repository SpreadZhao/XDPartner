package com.spread.xdpbusiness.xdpme

import com.bumptech.glide.Glide
import com.spread.xdpbusiness.xdpme.databinding.FragmentMeBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdplib.adapter.datamanager.UserManager
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class MeFragment : BaseViewBindingFragment<FragmentMeBinding>() {
    override fun getViewBinding(): FragmentMeBinding {
        return FragmentMeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        searchDetail()
        binding.loginOut.setOnClickListener{
            this@MeFragment.activity?.let { it1 -> PageUtil.gotoActivityIfExist(it1, ArouterUtil.PATH_ACTIVITY_LOGIN) }
        }
    }

    private fun searchDetail(){
        val userDetail = UserManager.getInstance().getUserDetail()
        userDetail?.let {
            binding.layoutPerson.initTextView(it)
            binding.tvName.text = it.nickName
            binding.tvPerson.text = "个人简介：${it.myDescription}"
            Glide.with(requireContext()).load(it.icon).into(binding.header)
            binding.pictures.setImageUrls(it.picture)
        }
        binding.layoutPerson.intList(35)
    }

    override fun onDestroy() {
        super.onDestroy()
        PageCurrentDataManager.initAll()
    }
}