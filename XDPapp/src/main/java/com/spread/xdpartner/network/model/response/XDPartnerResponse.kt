package com.spread.xdpartner.network.model.response

// https://www.jianshu.com/p/a98156d08337
interface XDPartnerResponse {
  fun code(): Int?
  fun msg(): String?
}
