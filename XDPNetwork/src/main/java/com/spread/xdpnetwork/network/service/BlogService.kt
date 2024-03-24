package com.spread.xdpnetwork.network.service

import com.spread.xdpnetwork.network.model.response.ThreadResponse
import com.spread.xdpnetwork.network.model.response.ThreadsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
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

  @GET("wz/blog/query/{id}")
  fun queryThreadById(
    @Path("id") id: Int,
    @Header("token") token: String?
  ): Call<ThreadResponse>
}