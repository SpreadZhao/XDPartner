package com.spread.xdpbusiness.xdpmessgae

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.gson.Gson
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemMessageListBinding
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.entry.MessageFiendBean
import com.spread.xdplib.adapter.utils.MmkvUtil
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdplib.adapter.utils.TestLogger
import com.tencent.mmkv.MMKV

class MessageListAdapter(private val context: Context) :
    RecyclerView.Adapter<MessageListAdapter.MessageHolder>() {
    private var mData: MutableList<MessageFiendBean> = mutableListOf()

    fun setData(data: List<MessageFiendBean>, position: Int) {
        mData.clear()
        for (message: MessageFiendBean in data) {
            if (message.isFriend != position) {
                mData.add(message)
            }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val binding = ItemMessageListBinding.inflate(LayoutInflater.from(context), parent, false)
        return MessageHolder(binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private var isDo = false
    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        if(!isDo) {
            holder.binding.number.setNumber(mData[position].count)
        } else{
            holder.binding.number.visibility = View.GONE
        }
        Glide.with(context).load(mData[position].userVo.icon).transform(CenterCrop())
            .into(holder.binding.header)
        holder.binding.name.text = mData[position].userVo.nickName
        holder.binding.message.text = mData[position].messages[0].content
        holder.binding.header.setOnClickListener {
            PageUtil.gotoActivityWithUserId(
                ArouterUtil.PATH_ACTIVITY_PERSON_DETAIL,
                mData[position].userVo.id
            )
        }

        holder.binding.root.setOnClickListener {
            val toJson = Gson().toJson(mData[position].messages)
            TestLogger.logd("toJson $toJson")
            TestLogger.logd("toJson ${mData[position].userVo}")
            ARouter.getInstance().build(ArouterUtil.PATH_ACTIVITY_MESSAGE_IM)
                .withParcelable(ArouterUtil.KEY_USER_VO, mData[position].userVo)
                .withString(ArouterUtil.KEY_USER_MESSAGE,toJson).navigation()
            isDo = true

        }
    }

    inner class MessageHolder(val binding: ItemMessageListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}