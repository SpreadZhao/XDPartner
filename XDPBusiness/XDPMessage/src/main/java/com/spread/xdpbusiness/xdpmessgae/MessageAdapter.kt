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
import com.spread.xdplib.adapter.entry.Message
import com.spread.xdplib.adapter.entry.MessageBean
import com.spread.xdplib.adapter.entry.UserVo
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.service.LoginServiceSingle
import java.util.Arrays

class MessageAdapter(private val context: Context, val userVo: UserVo) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_LEFT = 1
        const val TYPE_RIGHT = 2
        const val TYPE_TOP = 0
    }

    private var current = 1
    private var mData: MutableList<Message> = mutableListOf()
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
        LoginServiceSingle.instance.history(NetworkConstant.userId.toInt(), current) {
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
        return if (mData[position - 1].fromId.toLong() == NetworkConstant.userId) {
            TYPE_RIGHT
        } else {
            TYPE_LEFT
        }
    }

    fun sendMessage(bean: MessageBean) {
        val message = Message(
            type = 1,
            id = bean.toId,
            fromId = bean.fromId,
            content = bean.content,
            createTime = bean.createTime
        )
        mData.add(message)
        notifyItemRangeChanged(mData.size - 1, 1)
    }

    fun addMessage(list: List<Message>) {
        mData.addAll(list)
        logd("addMessage $list")
        notifyDataSetChanged()
    }

    inner class MessageLeftHolder(val binding: ItemMessageLeftBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MessageRightHolder(val binding: ItemMessageRightBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MessageTopHolder(val binding: ItemTopBinding) :
        RecyclerView.ViewHolder(binding.root)
}