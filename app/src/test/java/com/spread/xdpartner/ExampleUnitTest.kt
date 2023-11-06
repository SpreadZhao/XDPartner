package com.spread.xdpartner

import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("Spread Zhao")
        val doc = Jsoup.connect("https://xdu-partner.be.wizzstudio.com/wz/user/login?stuId=20009200303&password=Spreadzhao123&vcode=")
            .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
            .data("stuId", "20009200303")
            .data("password", "Spreadzhao123")
            .data("vcode", "")
            .ignoreContentType(true)
//            .header("Content-Type", "text/plain")
            .post()
        val res = doc.body().toString()
        println("result: $res")
    }
}