package com.spread.xdplib.adapter.customsview.imageview

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.spread.xdplib.R
import com.spread.xdplib.databinding.ItemPhotoViewBinding

object ImageFillDialog {
    fun showImageDialog(context: Context, imageUrls: List<String>, initialPosition: Int) {
        val dialog = Dialog(context,R.style.FullScreenDialog).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setContentView(R.layout.dialog_image) // 这是你的ViewPager2布局文件
        }
        val viewPager = dialog.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.apply {
            adapter = ImageViewPagerAdapter(context, imageUrls).apply {
                setOnRootViewClick{
                    dialog.dismiss()
                }
            }
            setCurrentItem(initialPosition, false)
        }
        dialog.show()
    }
}
class ImageViewPagerAdapter(
    private val context: Context,
    private val imageUrls: List<String>
) : RecyclerView.Adapter<ImageViewPagerAdapter.ImageViewHolder>(){
    private var onRootViewClick:(()->Unit)? = null
    fun setOnRootViewClick(listener:(()->Unit)){
        onRootViewClick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemPhotoViewBinding.inflate(LayoutInflater.from(context),parent,false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(imageUrls[position]).into(holder.binding.zoomImageView)
        holder.binding.rootView.setOnClickListener{
            onRootViewClick?.invoke()
        }
        holder.binding.indexView.text  = "${position + 1} / ${imageUrls.size}"
    }

    inner class ImageViewHolder(val binding:ItemPhotoViewBinding) :RecyclerView.ViewHolder(binding.root)

}
