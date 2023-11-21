package com.spread.xdpartner.network.service

import com.spread.xdpartner.network.model.ThreadsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BlogService {
  @GET("wz/blog/queryHottestBlog")
  fun queryHottestThreads(
    @Query("current") current: Int?,
    @Header("token") token: String?
  ): Call<ThreadsResponse>

  @GET("wz/blog/queryNewestBlog")
  fun queryNewestThreads(
    @Query("current") current: Int?,
    @Header("token") token: String?
  ): Call<ThreadsResponse>
}