package com.spread.xdpbusiness.xdpmessgae

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemMessageLeftBinding
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemMessageRightBinding
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemTopBinding
import com.spread.xdpcommon.BlogListAdapter
import com.spread.xdplib.adapter.datamanager.UserManager
import com.spread.xdplib.adapter.entry.MessageBean
import com.spread.xdplib.adapter.entry.UserVo
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class MessageAdapter(private val context: Context, val userVo: UserVo) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_LEFT = 1
        const val TYPE_RIGHT = 2
        const val TYPE_TOP = 0
    }

    private var current = 1
    private var mData: MutableList<MessageBean> = mutableListOf()
    private var showFinished = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        when (viewType) {
            TYPE_TOP -> {
                val binding = ItemTopBinding.inflate(layoutInflater, parent, false)
                return MessageTopHolder(binding)
            }

            TYPE_LEFT -> {
                val binding = ItemMessageLeftBinding.inflate(layoutInflater, parent, false)
                return MessageLeftHolder(binding)
            }

            TYPE_RIGHT -> {
                val binding = ItemMessageRightBinding.inflate(layoutInflater, parent, false)
                return MessageRightHolder(binding)
            }
        }
        return super.createViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return mData.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MessageLeftHolder) {
            Glide.with(context).load(userVo.icon).into(holder.binding.header)
            holder.binding.message.text = mData[position - 1].content

        }
        if (holder is MessageRightHolder) {
            Glide.with(context).load(UserManager.getInstance().getUserDetail()?.icon)
                .into(holder.binding.header)
            holder.binding.message.text = mData[position - 1].content
        }
        if (holder is MessageTopHolder) {
            if (showFinished) {
                holder.binding.text.text = "已全部加载完毕"
            }
            holder.binding.root.setOnClickListener {
                if (!showFinished)
                    searchData()
            }
        }
    }

    private fun searchData() {
        LoginServiceSingle.instance.history(UserManager.getInstance().getUserId(), current) {
            if (it.isEmpty()) {
                setShowFooter(true)
            } else {
                mData.addAll(0, it)
                notifyDataSetChanged()
            }
        }

    }

    fun setShowFooter(show: Boolean) {
        showFinished = show
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_TOP
        return if (mData[position - 1].fromId.toInt() == UserManager.getInstance().getUserId()) {
            TYPE_RIGHT
        } else {
            TYPE_LEFT
        }
    }

    fun sendMessage(bean: MessageBean) {
        mData.add(bean)
        notifyItemRangeChanged(mData.size - 1, 1)
    }

    inner class MessageLeftHolder(val binding: ItemMessageLeftBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MessageRightHolder(val binding: ItemMessageRightBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MessageTopHolder(val binding: ItemTopBinding) :
        RecyclerView.ViewHolder(binding.root)
}