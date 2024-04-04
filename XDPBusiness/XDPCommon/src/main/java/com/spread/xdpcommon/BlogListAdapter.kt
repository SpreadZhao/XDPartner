package com.spread.xdpcommon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.utils.StringUtils
import com.spread.xdplib.databinding.LayoutBlogBinding
import com.spread.xdplib.databinding.LayoutFootviewBinding
import com.spread.xdpnetwork.network.service.LoginServiceSingle

class BlogListAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private var mData: MutableList<Blog> = mutableListOf()
    private val TYPE_FOOTVIEW: Int = 1 //item类型：footview
    private val TYPE_ITEMVIEW: Int = 2 //item类型：itemview
    private var typeItem = TYPE_ITEMVIEW
    private var showFinished = false

    //定义footview附加到Window上时的回调
    private lateinit var footViewAttachedToWindowListener: () -> Unit
    fun setOnFootViewAttachedToWindowListener(pListener: () -> Unit) {
        this.footViewAttachedToWindowListener = pListener
    }

    fun setShowFooter(show: Boolean) {
        showFinished = show
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (typeItem == TYPE_ITEMVIEW) {
            //判断样式类型（列表布局、网格布局）
            val binding = LayoutBlogBinding.inflate(LayoutInflater.from(context), parent, false)
            return BlogViewHolder(binding)
        }
        //如果是footview
        else {
            val binding = LayoutFootviewBinding.inflate(LayoutInflater.from(context), parent, false)
            return FootViewHolder(binding)
        }

    }

    override fun getItemCount(): Int {
//        logd("getItemCount:  ${mData.size +1}")
        return mData.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        //设置在数据最底部显示footview
        typeItem = if (position == mData.size) TYPE_FOOTVIEW else TYPE_ITEMVIEW
        return typeItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is BlogViewHolder) {
            mData.let {
                val blog = it[position]
                holder.binding.apply {
                    ivContent.text = blog.content
                    tvName.text = blog.userVo.nickName
                    tvTime.text = blog.updateTime
                    tvLovePerson.text = blog.liked.toString()
                    tvPerson.text = blog.viewTimes.toString()
                    ivTitle.text = blog.title
                    val message = StringUtils.getBlogPeopleText(blog.absent,blog.whenMeet,blog.location)
                    if(message.isEmpty()){
                        ivMessage.visibility = View.GONE
                    }else {
                        ivMessage.visibility = View.VISIBLE
                        ivMessage.text = message
                    }
                    if (blog.images.isNotEmpty()) {
                        Glide.with(context).load(blog.images[0]).transform(CenterCrop())
                            .into(ivImage)
                        if (blog.images.size >= 2) {
                            Glide.with(context).load(blog.images[1]).transform(CenterCrop())
                                .into(ivImage2)
                        }
                    } else {
                        ivImage.visibility = View.GONE
                        ivImage2.visibility = View.GONE
                    }
                    Glide.with(context).load(it[position].userVo.icon).transform(CenterCrop())
                        .into(ivHeader)
                    ivLove.setOnClickListener {
                        LoginServiceSingle.instance.likeBlog(blog.id) {
                            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
                            ivLove.isSelected = !ivLove.isSelected
                            if(ivLove.isSelected) tvLovePerson.text = String.format("%d", (tvLovePerson.text.toString().toInt() +1))
                            else tvLovePerson.text = String.format("%d", (tvLovePerson.text.toString().toInt() - 1))
                        }
                    }
                }
            }
        }
        if (holder is FootViewHolder) {
            if (showFinished)
                holder.binding.text.text = "已全部加载完毕"
        }
    }

    fun setData(data: List<Blog>) {
        if (data.isEmpty()) {
            setShowFooter(true)
        } else {
            mData += data
        }
        notifyDataSetChanged()
    }

    /**
     * 当footview第二次出现在列表时，回调该事件
     * （此处用于模拟用户上滑手势，当滑到底部时，重新请求数据）
     */
    var footviewPosition = 0
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (footviewPosition == holder.adapterPosition) {
            return
        }
        if (holder is FootViewHolder) {
            footviewPosition = holder.adapterPosition
            //回调查询事件
            footViewAttachedToWindowListener.invoke()
        }
    }

    inner class BlogViewHolder(val binding: LayoutBlogBinding) : ViewHolder(binding.root) {

    }

    inner class FootViewHolder(val binding: LayoutFootviewBinding) : ViewHolder(binding.root) {

    }
}