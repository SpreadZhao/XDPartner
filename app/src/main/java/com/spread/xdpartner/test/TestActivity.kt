package com.spread.xdpartner.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.xdpartner.R
import com.spread.xdpartner.network.NetworkConstant
import com.spread.xdpartner.network.service.BlogService
import com.spread.xdpartner.network.service.ServiceCreator
import com.spread.xdpartner.test.TestLogger.log

class TestActivity : AppCompatActivity() {

  private lateinit var queryHottestThreadsBtn: Button
  private lateinit var queryNewestThreadsBtn: Button
  private lateinit var queryByIdInput: EditText
  private lateinit var queryByIdBtn: Button

  private lateinit var service: BlogService

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)
    queryHottestThreadsBtn = findViewById(R.id.query_hottest_threads)
    queryNewestThreadsBtn = findViewById(R.id.query_newest_threads)
    queryByIdInput = findViewById(R.id.query_by_id_input)
    queryByIdBtn = findViewById(R.id.query_by_id_btn)
    service = ServiceCreator.create(BlogService::class.java)
    queryHottestThreadsBtn.setOnClickListener {
      log("Start query Hottest threads.")
      service.queryHottestThreads(1, NetworkConstant.PERMANENT_TOKEN)
        .enqueue(TestCallBackManager.threadsCallback)
    }
    queryNewestThreadsBtn.setOnClickListener {
      log("Start query newest threads")
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
      log("Start query thread by id: $id")
      service.queryThreadById(id, NetworkConstant.PERMANENT_TOKEN)
        .enqueue(TestCallBackManager.threadCallback)
    }
  }


}