package com.spread.xdplib.adapter.customsview.imageview
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.spread.xdplib.adapter.utils.TestLogger.logd
import kotlin.math.abs
import kotlin.math.ceil

class NineGridImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var imageUrls = listOf<String>()
    private var imageViews = mutableListOf<ImageView>()
    private val spacing = 10 // 图片之间的间距，可以根据需要调整

    fun setImageUrls(urls: List<String>) {
        imageUrls = urls
        imageViews.clear()
        removeAllViews() // 移除所有子View

        // 为每个URL创建一个ImageView，并使用Glide加载图片
        urls.forEachIndexed  { i,url ->
            val imageView = ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setOnClickListener{
                    ImageFillDialog.showImageDialog(context,urls,i)
                }
            }
            Glide.with(context).load(url).into(imageView)
            addView(imageView)
            imageViews.add(imageView)
        }

        requestLayout() // 通知系统重新布局
    }

    // 假设宽度固定，仅为示例简化计算，实际开发时应根据具体需求调整
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val totalWidth = width - paddingLeft - paddingRight
        val imageSize = (totalWidth - spacing * 2) / 3
        val rows = ceil(imageUrls.size / 3.0).toInt()
        val heightSpan = abs(rows - 1)
        val totalHeight = rows * imageSize + heightSpan * spacing
        setMeasuredDimension(width, totalHeight + paddingTop + paddingBottom)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val totalWidth = r - l
        val imageSize = (totalWidth - paddingLeft - paddingRight - spacing * 2) / 3
        for ((index, view) in imageViews.withIndex()) {
            val row = index / 3
            val col = index % 3
            val left = paddingLeft + col * (imageSize + spacing)
            val top = paddingTop + row * (imageSize + spacing)
            view.layout(left, top, left + imageSize, top + imageSize)
        }
    }

}
