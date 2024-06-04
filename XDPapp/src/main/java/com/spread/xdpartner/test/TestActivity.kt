package com.spread.xdpartner.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xdpartner.R
import com.spread.xdpartner.main.MainActivity
import com.spread.xdpartner.test.adapter.TestAdapterType
import com.spread.xdpartner.test.adapter.TestButtonAdapter
import com.spread.xdpartner.test.adapter.TestEditButtonAdapter
import com.spread.xdplib.adapter.MultiTypeAdapter
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.utils.PageUtil
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.TestCallBackManager
import com.spread.xdpnetwork.network.service.BlogService
import com.spread.xdpnetwork.network.service.ServiceCreator

class TestActivity : AppCompatActivity() {

  private lateinit var queryHottestThreadsBtn: Button
  private lateinit var queryNewestThreadsBtn: Button
  private lateinit var queryByIdInput: EditText
  private lateinit var queryByIdBtn: Button

  private lateinit var service: BlogService

  private lateinit var  dataSet:List<MultiTypeData>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)
    init()
  }
  private fun initDataSet(){
    dataSet = listOf(
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_BUTTON, TestButtonAdapter.ButtonData("查询最热帖子") {
        Log.d("MultiTypeAdapter", "查询最热帖子点击")
        service.queryHottestThreads(1, NetworkConstant.PERMANENT_TOKEN)
          .enqueue(TestCallBackManager.threadsCallback)
      }),
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_BUTTON, TestButtonAdapter.ButtonData("查询最新帖子") {
        Log.d("MultiTypeAdapter", "查询最新帖子点击")
      }),
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_EDIT_BUTTON, TestEditButtonAdapter.EditButtonData("查询") {
        Log.d("MultiTypeAdapter", "查寻id")
      }),
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_BUTTON, TestButtonAdapter.ButtonData("跳转主页") {
        startActivity(Intent(this, MainActivity::class.java))
        Log.d("MultiTypeAdapter", "跳转主页")
      }),
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_BUTTON, TestButtonAdapter.ButtonData("跳转登录页面") {
        PageUtil.gotoActivityIfExist(this@TestActivity,ArouterUtil.PATH_ACTIVITY_LOGIN)
        Log.d("MultiTypeAdapter", "跳转登录页面")
      }),
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_BUTTON, TestButtonAdapter.ButtonData("测试自定义View") {
        startActivity(Intent(this, TestCustomViewActivity::class.java))
      }),
      MultiTypeData(TestAdapterType.ADAPTER_TYPE_EDIT_BUTTON, TestEditButtonAdapter.EditButtonData("更换ip") {
        Log.d("MultiTypeAdapter", "更换ip：$it")
        NetworkConstant.BASE_URL = it
      }),
    )
  }
  private fun init() {
//    service = ServiceCreator.create(BlogService::class.java)
    initDataSet()
    val layoutManager = LinearLayoutManager(this).apply {
      isItemPrefetchEnabled = false
    }
    val adapter = MultiTypeAdapter().apply {
      configDataSet(dataSet)
      addSubAdapter(TestAdapterType.ADAPTER_TYPE_BUTTON, TestButtonAdapter())
      addSubAdapter(TestAdapterType.ADAPTER_TYPE_EDIT_BUTTON, TestEditButtonAdapter())
    }
    val recyclerView = findViewById<RecyclerView>(R.id.test_recycler_view).apply {
      setHasFixedSize(true)
      setItemViewCacheSize(0)
      this.layoutManager = layoutManager
      this.adapter = adapter
    }
  }

  private fun legacy() {
    queryHottestThreadsBtn = findViewById(R.id.query_hottest_threads)
    queryNewestThreadsBtn = findViewById(R.id.query_newest_threads)
    queryByIdInput = findViewById(R.id.query_by_id_input)
    queryByIdBtn = findViewById(R.id.query_by_id_btn)
    service = ServiceCreator.create(BlogService::class.java)
    queryHottestThreadsBtn.setOnClickListener {
      logd("Start query Hottest threads.")
      service.queryHottestThreads(1, NetworkConstant.PERMANENT_TOKEN)
        .enqueue(TestCallBackManager.threadsCallback)
    }
    queryNewestThreadsBtn.setOnClickListener {
      logd("Start query newest threads")
      service.queryNewestThreads(1, NetworkConstant.PERMANENT_TOKEN)
        .enqueue(TestCallBackManager.threadsCallback)
    }
    queryByIdBtn.setOnClickListener {
      var id = 0
      try {
        id = queryByIdInput.text.toString().toInt()
      } catch (e: NumberFormatException) {
        e.printStackTrace()
      }
      logd("Start query thread by id: $id")
      service.queryThreadById(id, NetworkConstant.PERMANENT_TOKEN)
        .enqueue(TestCallBackManager.threadCallback)
    }
  }


}