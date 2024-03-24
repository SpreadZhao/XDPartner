package com.spread.xdpbusiness.xdpsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.utils.TestLogger.log
import com.spread.xdplib.databinding.LayoutBlogBinding

class BlogListAdapter(private val context: Context) :RecyclerView.Adapter<BlogListAdapter.BlogViewHolder>()  {

    private var mData:List<Blog>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val binding = LayoutBlogBinding.inflate(LayoutInflater.from(context),parent,false)
        return BlogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mData?.size ?:0
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {

        mData?.let {
            val blog = it[position]
            holder.binding.apply {
                ivContent.text = blog.content
                tvName.text = blog.userVo.nickName
                tvTime.text = blog.updateTime
                tvLovePerson.text = blog.liked.toString()
                tvPerson.text = blog.viewTimes.toString()
                log(blog.images.size.toString())
                if(blog.images.size>=1){
                    Glide.with(context).load(blog.images[0]).transform(CenterCrop()).into(ivImage)
                    if(blog.images.size>=2){
                        Glide.with(context).load(blog.images[1]).transform(CenterCrop()).into(ivImage2)
                    }
                } else {
                    ivImage.visibility = View.GONE
                    ivImage2.visibility = View.GONE
                }
                Glide.with(context).load(it[position].userVo.icon).transform(CenterCrop()).into(ivHeader)
            }
        }
    }

    fun setData(data:List<Blog>){
        mData = data
        notifyItemRangeChanged(0,data.size)
    }
    inner class BlogViewHolder(val binding: LayoutBlogBinding) :ViewHolder(binding.root){

    }
}