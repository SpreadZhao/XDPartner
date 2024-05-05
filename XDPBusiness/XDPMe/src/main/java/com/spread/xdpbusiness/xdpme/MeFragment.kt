package com.spread.xdpbusiness.xdpme

import com.bumptech.glide.Glide
import com.spread.xdpbusiness.xdpme.databinding.FragmentMeBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class MeFragment : BaseViewBindingFragment<FragmentMeBinding>() {
    override fun getViewBinding(): FragmentMeBinding {
        return FragmentMeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        searchDetail()
        binding.layoutLogin.setOnClickListener{

        }
    }

    private fun searchDetail(){
        LoginServiceSingle.instance.queryOther(35){
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