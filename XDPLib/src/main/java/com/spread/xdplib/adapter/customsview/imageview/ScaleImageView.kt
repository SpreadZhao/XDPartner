package com.spread.xdplib.adapter.customsview.imageview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.max
import kotlin.math.min

class ScaleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    private val TAG: String = "ScaleImageView"
    private var scaleFactor = 1.5f
    private var picture: Bitmap? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bigScale = 1f
    private var smallScale = 1f
    private var offsetX = 0f
    private var offsetY = 0f
    private var scale = 0f
    private var scaleAnimator: ValueAnimator? = null
    fun setPictureBitmap(tem: Bitmap) {
        picture = tem
        requestLayout()
        invalidate()
    }
    private var onRootViewClick:(()->Unit)? = null
    fun setRootViewClick(listener:(()->Unit)){
        onRootViewClick = listener
    }
    private val gestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                // 示例：双击时放大或还原
                val currentScale = scale
                val targetScale = if (scale < 2f) scale * 2f else scale * 0.5f // 假设目标缩放比例为2倍或原始大小
                startScaleAnimation(currentScale, targetScale, e)
                return true
            }
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                // 实现单击消失逻辑
                // 这里仅示例将视图设为不可见，具体实现可能根据需求而异
                onRootViewClick?.invoke()
                return true
            }
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                // 示例：拖拽时调整偏移量
                if (e2.pointerCount == 1) {
                    offsetX -= distanceX
                    offsetY -= distanceY
                    adjustOffsets()
                    invalidate()
                }
                return true
            }
        })

    private fun adjustOffsets() {
        picture?.let {
            // 图片缩放后的宽度和高度
            val scaledWidth = scale * it.width
            val scaledHeight = scale * it.height

            // 确保当图片比视图小或者刚好等大时，它是居中的
            if (scaledWidth < width) {
                offsetX = (width - scaledWidth) / 2
            } else {
                // 当图片宽度大于视图宽度时，限制offsetX，防止左边界和右边界超出
                offsetX = Math.max(offsetX, (width - scaledWidth) / 2)
                offsetX = Math.min(offsetX, (scaledWidth - width) / 2)
            }

            if (scaledHeight < height) {
                offsetY = (height - scaledHeight) / 2
            } else {
                // 当图片高度大于视图高度时，限制offsetY，防止上边界和下边界超出
                offsetY = Math.max(offsetY, (height - scaledHeight) / 2)
                offsetY = Math.min(offsetY, (scaledHeight - height) / 2)
            }
        }
    }

    private val scaleGestureDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val scaleFactor = detector.scaleFactor
                // 缩放前后保持缩放焦点不变，计算需要的偏移量调整
                val focusX = detector.focusX
                val focusY = detector.focusY
                val prevScale = scale
                scale *= scaleFactor
                scale = max(1f, min(scale, 5f))
                offsetX += (focusX - offsetX) - (focusX - offsetX) * scale / prevScale
                offsetY += (focusY - offsetY) - (focusY - offsetY) * scale / prevScale
                invalidate()
                return true
            }
        })
    private var lastX = 0f
    private var lastY = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // todo 判断movew事件， 如果滑动到图片边界了 交给父亲消费，如果没有滑动图片边界，view请求父亲不要拦截
//        val action = event.actionMasked
//        when (action) {
//            MotionEvent.ACTION_DOWN -> {
//                // 请求父视图不要拦截触摸事件，以便子视图可以处理它
//                if(isTouchInsideImage(event))
//                parent.requestDisallowInterceptTouchEvent(true)
//            }
//            MotionEvent.ACTION_MOVE -> {
//                // 调整此处逻辑，使用shouldParentIntercept决定是否允许父视图拦截
//                val distanceX = lastX - event.x
//                val distanceY = lastY - event.y
//                // 例如，当图片被放大并且触摸事件在图片范围内时
//                if (shouldParentIntercept(event, distanceX, distanceY)) {
//                    parent.requestDisallowInterceptTouchEvent(true)
//                }
//            }
//        }
        scaleGestureDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    private fun startScaleAnimation(currentScale: Float, targetScale: Float, e: MotionEvent) {
        // 先取消之前的动画（如果有的话）
        scaleAnimator?.cancel()
        // 创建并启动动画
        scaleAnimator = ValueAnimator.ofFloat(currentScale, targetScale).apply {
            duration = 300 // 动画时长
            addUpdateListener { animation ->
                val newScale = animation.animatedValue as Float
                val scaleFactorDynamic = newScale / scale
                scale = newScale
                offsetX = (1 - scaleFactorDynamic) * e.x + scaleFactorDynamic * offsetX
                offsetY = (1 - scaleFactorDynamic) * e.y + scaleFactorDynamic * offsetY
                invalidate()
            }
            start()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        picture?.let { bitmap ->
            val viewWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
            val viewHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
            val bitmapWidth = bitmap.width.toFloat()
            val bitmapHeight = bitmap.height.toFloat()
            val widthScale = viewWidth / bitmapWidth
            val heightScale = viewHeight / bitmapHeight
            // 使用较大的比例确保至少一个维度完全匹配
//            scale = max(widthScale, heightScale)
            scale = widthScale
            bigScale = scale * scaleFactor
            smallScale = scale
            // 计算偏移量使图片居中显示
            offsetX = (viewWidth - bitmapWidth * scale) / 2f
            offsetY = (viewHeight - bitmapHeight * scale) / 2f
            setMeasuredDimension(viewWidth.toInt(), viewHeight.toInt())
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        picture?.let { bitmap ->
            canvas.save()
            canvas.translate(offsetX, offsetY)
            canvas.scale(scale, scale)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            canvas.restore()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // 取消动画
        scaleAnimator?.cancel()
    }
}