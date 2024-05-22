package com.spread.xdpbusiness.xdpsearch

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.spread.xdpbusiness.xdpsearch.databinding.FragmentSearchBinding
import com.spread.xdplib.adapter.base.BaseViewBindingFragment
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.datamanager.PageCurrentDataManager
import com.spread.xdplib.adapter.entry.UserVo
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.service.LoginServiceSingle


class SearchFragment : BaseViewBindingFragment<FragmentSearchBinding>() {

    companion object {
        @JvmStatic
        val newestBlog = 1

        @JvmStatic
        val likeBlog = 2

        @JvmStatic
        val hottestBlog = 3
    }

    private val titles by lazy(LazyThreadSafetyMode.NONE) {
        listOf("最新", "猜你喜欢", "最热")
    }
    private val viewPagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ViewPagerAdapter(requireContext(), this@SearchFragment)
    }

    override fun initView() {
        searchData()
        binding.list.adapter = viewPagerAdapter
        // No sliding
        binding.list.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.list) { tab, position ->
            tab.text = titles[position]
        }.attach()
        // No sliding
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                PageCurrentDataManager.initAll()
                // 第二个参数设置为false以禁用切换动画
                binding.list.setCurrentItem(tab.position, false)
                setSelectedTabChoose(true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {
                PageCurrentDataManager.initAll()
                viewPagerAdapter.notifyItemRangeChanged(tab.position, 1)
                setSelectedTabChoose(true)
            }
        })
        binding.list.currentItem = 1
        binding.searchBar.setSearchListener {
            if (it.isNullOrEmpty()) return@setSearchListener
            viewPagerAdapter.updateBlogList(it, binding.list.currentItem)
            setSelectedTabChoose(false)
        }
        binding.cvStudy.setOnClickListener {
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE, 1)
        }
        binding.cvHappy.setOnClickListener {
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE, 2)
        }
        binding.cvLove.setOnClickListener {
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE, 3)
        }
        binding.cvLife.setOnClickListener {
            PageUtil.gotoActivityWithType(ArouterUtil.PATH_ACTIVITY_SEARCH_TYPE, 4)
        }
        binding.tvTest.setOnClickListener {
            showNotification(requireContext(), "test", "你好")
        }
    }


    @SuppressLint("LaunchActivityFromNotification")
    private fun showNotification(context: Context, title: String, message: String) {
        val channelId = "my_channel_id"
        val channelName = "My_Channel"
        val notificationId = 1
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 创建通知渠道（仅适用于Android 8.0及以上版本）
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
        // 创建触发函数的意图
        val functionIntent = Intent(context, YourBroadcastReceiver::class.java)
        functionIntent.action = "com.example.ACTION_TRIGGER_FUNCTION"
        functionIntent.putExtra("message", "123")

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            functionIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // 创建通知构建器
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(com.spread.xdplib.R.drawable.add_photo)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // 弹出通知
        notificationManager.notify(notificationId, builder.build())
    }


    private fun setSelectedTabChoose(isChoose: Boolean) {
        if (isChoose) {
            binding.tabLayout.setTabTextColors(
                requireContext().getColor(com.spread.xdplib.R.color.black),
                requireContext().getColor(
                    com.spread.xdplib.R.color.purple
                )
            )
            binding.tabLayout.setSelectedTabIndicatorColor(requireContext().getColor(com.spread.xdplib.R.color.purple))
        } else {
            binding.tabLayout.setTabTextColors(
                requireContext().getColor(com.spread.xdplib.R.color.black),
                requireContext().getColor(
                    com.spread.xdplib.R.color.black
                )
            )
            binding.tabLayout.setSelectedTabIndicatorColor(requireContext().getColor(com.spread.xdplib.R.color.transparency))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        PageCurrentDataManager.initAll()
    }

    override fun onResume() {
        super.onResume()
        initView()
    }
    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    private fun searchData() {
        LoginServiceSingle.instance.queryOther(NetworkConstant.userId) {
            binding.tvName.text = "${it.nickName}，你好"
            Glide.with(requireContext()).load(it.icon).into(binding.circle)
        }
    }
}

class YourBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.example.ACTION_TRIGGER_FUNCTION") {
            val message = intent.getStringExtra("message")
            if (message != null) {
                yourFunction(message) // 调用你自己的函数，传递参数"123"
            }
        }
    }

    private fun yourFunction(message: String) {
        // 在这里执行你想要的操作，比如打印"123"
        val tojson = "[{\"content\":\"你好\",\"fromId\":14,\"id\":382,\"type\":1}]"
        val userVo = UserVo(
            icon = "https://xdu-partner.oss-cn-hangzhou.aliyuncs.com/upload/2024-04-02/f778417a-ec05-401d-8ad6-bd1a17c76c23-35.jpg",
            id = 35,
            nickName = "test"
        )
        ARouter.getInstance().build(ArouterUtil.PATH_ACTIVITY_MESSAGE_IM)
            .withParcelable(ArouterUtil.KEY_USER_VO, userVo)
            .withString(ArouterUtil.KEY_USER_MESSAGE, tojson).navigation()
    }
}