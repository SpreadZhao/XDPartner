package com.spread.xdpbusiness.xdpmessgae

import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.spread.xdpbusiness.xdpmessgae.databinding.ActivityImBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.datamanager.UserManager
import com.spread.xdplib.adapter.entry.MessageBean
import com.spread.xdplib.adapter.entry.UserVo
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdplib.adapter.utils.closeSoftInput
import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.service.LoginServiceSingle
import kotlin.math.log


@Route(path = ArouterUtil.PATH_ACTIVITY_MESSAGE_IM)
class IMActivity : BaseViewBindingActivity<ActivityImBinding>() {

    @Autowired(name = ArouterUtil.KEY_USER_VO)
    @JvmField
    var keyUserVo: UserVo = UserVo("", 0, "")
    private lateinit var mAdapter :MessageAdapter
    override fun getViewBinding(): ActivityImBinding {
        return ActivityImBinding.inflate(layoutInflater)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    private fun closeKeyBoard() {
        logd("closeKeyBoard")
        if (currentFocus != null && currentFocus!!.windowToken != null) {
            val v = currentFocus
            logd("clear")
           closeSoftInput(this, v)
        }
    }
    override fun initView() {
        ARouter.getInstance().inject(this)
        mAdapter = MessageAdapter(this,keyUserVo)
        binding.titleBar.apply {
            setTitleText(keyUserVo.nickName)
            setLeftListener {
                finish()
            }
        }
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = mAdapter
        }
        binding.send.setSendType()
        binding.send.setSearchListener {
            closeKeyBoard()
            if (it == null) return@setSearchListener
            val bean =
                MessageBean(
                    content = it,
                    fromId = NetworkConstant.userId,
                    toId = keyUserVo.id,
                    createTime = System.currentTimeMillis().toString(),
                    type = "1",
                    command = 1
                )
            LoginServiceSingle.instance.sendMessage(bean) {
                data-> Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
                if(data == "发送成功"){
                    mAdapter.sendMessage(bean)
                    binding.send.clearEditText()
                }
            }
        }
    }


}