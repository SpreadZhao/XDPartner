package com.spread.xdpartner.network.legacy

import com.spread.xdpartner.network.NetworkConstant
import org.jsoup.Connection
import org.jsoup.Jsoup

object Jsouper {
  fun login(stuId: String, password: String): String {
    val doc = Jsoup.connect("${NetworkConstant.BASE_URL}wz/user/login?stuId=${stuId}&password=${password}&vcode=")
      .defaultPost()
    return doc.body().toString()
  }

  fun getLatestThread(current: Int): String {
    val doc = Jsoup.connect("${NetworkConstant.BASE_URL}wz/blog/queryNewestBlog")
      .data("current", current.toString())
      .defaultGet()
    return doc.body().toString()
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