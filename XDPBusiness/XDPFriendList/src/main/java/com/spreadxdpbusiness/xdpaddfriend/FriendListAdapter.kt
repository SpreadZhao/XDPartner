package com.spreadxdpbusiness.xdpaddfriend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdplib.adapter.entry.Friend
import com.spreadxdpbusiness.friendlist.databinding.ItemFriendBinding

class FriendListAdapter : RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    private var items = emptyList<Friend>()

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
        holder.binding.name.text = items[position].nickName
        holder.binding.root.setOnClickListener{
            mClickListener?.invoke(position)
        }
    }

    fun setItems(friends:List<Friend>){
        items = friends
        notifyDataSetChanged()
    }

    fun setClickListener(listener : ((Int)->Unit)){
        mClickListener = listener
    }
    inner class FriendViewHolder(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root)
}