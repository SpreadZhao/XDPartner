package com.spreadxdpbusiness.xdpaddfriend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spread.xdplib.adapter.entry.UserVo
import com.spreadxdpbusiness.friendlist.databinding.ItemFriendBinding

class FriendListAdapter(private val context: Context) : RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>(){

    private var items = emptyList<UserVo>()

    private var mClickListener : ((Int)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
//        holder.binding.header = items[position]; 设置图片
        Glide.with(context).load(items[position].icon).into(holder.binding.header)
        holder.binding.name.text = items[position].nickName
        holder.binding.root.setOnClickListener{
            mClickListener?.invoke(position)
        }
    }

    fun setItems(friends:List<UserVo>){
        items = friends
        notifyItemRangeChanged(0,friends.size)
    }

    fun setClickListener(listener : ((Int)->Unit)){
        mClickListener = listener
    }
    inner class FriendViewHolder(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root)

}