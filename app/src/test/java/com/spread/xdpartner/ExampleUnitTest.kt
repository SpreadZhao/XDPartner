package com.spread.xdpartner

import com.spread.xdpartner.network.NetworkConstant.PERMANENT_TOKEN
import com.spread.xdpartner.network.legacy.Jsouper
import com.spread.xdpartner.network.model.ThreadsResponse
import com.spread.xdpartner.network.service.BlogService
import com.spread.xdpartner.network.service.ServiceCreator
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("Spread Zhao")
        println(
            Jsouper.login(
            stuId = "20009200612",
            password = "chr13579shy6"
        ))
    }

    @Test
    fun testGetLatestThread() {
//        println(Jsouper.getLatestThread(1))
        val service = ServiceCreator.create(BlogService::class.java)
        println("start retrofit request")
        service.queryHottestThreads(1, PERMANENT_TOKEN)
            .enqueue(object : Callback<ThreadsResponse> {
                override fun onResponse(
                    call: Call<ThreadsResponse>,
                    response: Response<ThreadsResponse>
                ) {
                    println("Response code: ${response.code()}")
                    println("Response body: ${response.body()}")
                }

                override fun onFailure(call: Call<ThreadsResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        println("retrofit request end")
    }

    @Test
    fun testGetHottestThread() {
        println(Jsouper.getHotestThread(1))
    }

}