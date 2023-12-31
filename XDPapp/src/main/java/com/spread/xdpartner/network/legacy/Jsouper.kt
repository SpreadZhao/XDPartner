package com.spread.xdpartner.network.legacy

import com.spread.xdpartner.network.NetworkConstant
import com.spread.xdpartner.network.NetworkConstant.PERMANENT_TOKEN
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.StringBuilder

object Jsouper {

  fun login(stuId: String, password: String): String {
    val doc = Jsoup.connect("${NetworkConstant.BASE_URL}wz/user/login?stuId=${stuId}&password=${password}&vcode=")
      .defaultPost()
    return doc.body().toString()
  }

  fun getLatestThread(current: Int): String {
    val doc = Jsoup.connect("${NetworkConstant.BASE_URL}wz/blog/queryNewestBlog")
      .data("current", current.toString())
      .header("token", PERMANENT_TOKEN)
      .defaultGet()
    return doc.body().toString()
  }

  fun getHotestThread(current: Int): String {
    val doc = Jsoup.connect("${NetworkConstant.BASE_URL}wz/blog/queryHottestBlog")
      .data("current", current.toString())
      .header("token", PERMANENT_TOKEN)
      .defaultGet()
    return doc.body().toString()
  }

  fun queryThreadById(id: Int): String {
    val doc = Jsoup.connect("${NetworkConstant.BASE_URL}wz/blog/query/${id}")
      .header("token", PERMANENT_TOKEN)
      .defaultGet()
    return doc.body().toString()
  }

  private fun Document.parse() = StringBuilder().run {

    toString()
  }

  private fun Connection.addDefaultHeader() = apply {
    this.header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
  }

  private fun Connection.defaultSettings() = apply {
    this.ignoreContentType(true)
    this.ignoreHttpErrors(true)
  }

  private fun Connection.defaultPost() = let {
    it.addDefaultHeader().defaultSettings().post()
  }

  private fun Connection.defaultGet() = let {
    it.addDefaultHeader().defaultSettings().get()
  }

}