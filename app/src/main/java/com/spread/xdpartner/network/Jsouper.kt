package com.spread.xdpartner.network

import org.jsoup.Jsoup

object Jsouper {
  fun login(stuId: String, password: String) {
    val doc = Jsoup.connect("https://xdu-partner.be.wizzstudio.com/wz/user/login?stuId=20009200303&password=Spreadzhao123&vcode=")
      .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
      .data("stuId", stuId)
      .data("password", password)
      .data("vcode", "")
      .ignoreContentType(true)
      .post()
    val res = doc.body().toString()
  }
}