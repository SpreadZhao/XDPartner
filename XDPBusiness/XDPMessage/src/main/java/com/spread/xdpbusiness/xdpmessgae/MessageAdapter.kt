package com.spread.xdpbusiness.xdpmessgae

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemMessageLeftBinding
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemMessageRightBinding
import com.spread.xdpbusiness.xdpmessgae.databinding.ItemTopBinding
import com.spread.xdplib.adapter.datamanager.UserManager
import com.spread.xdplib.adapter.entry.MessageBean

class MessageAdapter(private val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val TYPE_LEFT = 1
        const val TYPE_RIGHT = 2
        const val TYPE_TOP = 0
    }
    private var mData : MutableList<MessageBean> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        when(viewType){
            TYPE_TOP-> {
                val binding = ItemTopBinding.inflate(layoutInflater, parent, false)
                return MessageTopHolder(binding)
            }
            TYPE_LEFT-> {
                val binding = ItemMessageLeftBinding.inflate(layoutInflater, parent, false)
                return MessageLeftHolder(binding)
            }
            TYPE_RIGHT-> {
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

    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0)
            return TYPE_TOP
        return if(mData[position - 1].fromId.toInt() == UserManager.getInstance().getUserId()){
            TYPE_RIGHT
        } else {
            TYPE_LEFT
        }
    }
    inner class MessageLeftHolder(val binding:ItemMessageLeftBinding) :RecyclerView.ViewHolder(binding.root){

    }

    inner class MessageRightHolder(val binding:ItemMessageRightBinding) :RecyclerView.ViewHolder(binding.root){

    }

    inner class MessageTopHolder(val binding:ItemTopBinding):RecyclerView.ViewHolder(binding.root)
}