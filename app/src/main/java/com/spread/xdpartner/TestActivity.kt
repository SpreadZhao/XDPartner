package com.spread.xdpartner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.xdpartner.R
import com.spread.xdpartner.network.NetworkConstant
import com.spread.xdpartner.network.model.ThreadsResponse
import com.spread.xdpartner.network.service.BlogService
import com.spread.xdpartner.network.service.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

  private val TAG = "TestActivity"

  private lateinit var queryHottestThreadsBtn: Button

  private val threadsCallback = object : Callback<ThreadsResponse> {
    override fun onResponse(
      call: Call<ThreadsResponse>,
      response: Response<ThreadsResponse>
    ) {
      log("Response code: ${response.code()}")
      log("Response body: ${response.body()}")
    }

    override fun onFailure(call: Call<ThreadsResponse>, t: Throwable) {
      t.printStackTrace()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)
    queryHottestThreadsBtn = findViewById(R.id.query_hottest_threads)
    queryHottestThreadsBtn.setOnClickListener {
      val service = ServiceCreator.create(BlogService::class.java)
      println("start retrofit request")
      service.queryHottestThreads(1, NetworkConstant.PERMANENT_TOKEN)
        .enqueue(threadsCallback)
      println("retrofit request end")
    }
  }

  private fun log(msg: String) = Log.d(TAG, msg)
}