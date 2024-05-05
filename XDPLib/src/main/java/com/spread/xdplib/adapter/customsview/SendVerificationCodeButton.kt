package com.spread.xdplib.adapter.customsview

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import com.spread.xdplib.adapter.utils.TestLogger.logd

class SendVerificationCodeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatButton(context, attrs, defStyleAttr) {

    private var countDownTimer: CountDownTimer? = null
    private var mListener: (() -> Unit)? =null
    init {
        text = "发送验证码"
        setOnClickListener {
            mListener?.invoke()
            startCountDown()
        }
    }

    private fun startCountDown() {
        logd("发送验证码")
        if (countDownTimer != null) {
            return
        }

        isEnabled = false
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                text = "发送验证码"
                isEnabled = true
                countDownTimer = null
            }
        }.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        countDownTimer?.cancel()
    }
    fun setListener(listener:(()->Unit)) {
        mListener = listener
    }
}