package com.spread.xdpartner

import com.spread.xdpartner.network.legacy.Jsouper
import org.junit.Test

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
        println(Jsouper.getLatestThread(1))
    }
}