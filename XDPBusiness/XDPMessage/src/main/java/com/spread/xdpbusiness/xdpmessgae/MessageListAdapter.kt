package com.spread.xdpbusiness.xdpmessgae

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemMessageListBinding
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.entry.MessageFiendBean
import com.spread.xdplib.adapter.utils.PageUtil

class MessageListAdapter(private val context: Context) :
    RecyclerView.Adapter<MessageListAdapter.MessageHolder>() {
    private var mData: MutableList<MessageFiendBean> = mutableListOf()

    fun setData(data: List<MessageFiendBean>, position: Int) {
        mData.clear()
        for (message:MessageFiendBean in data){
            if(message.isFriend==position){
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

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.binding.number.setNumber(mData[position].count)
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
        holder.binding.root.setOnClickListener{
            PageUtil.gotoActivityWithUserVo(
                ArouterUtil.PATH_ACTIVITY_MESSAGE_IM,
                mData[position].userVo
            )
        }
    }

    inner class MessageHolder(val binding: ItemMessageListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}