package com.spread.xdpnetwork.network.service

import com.spread.xdplib.adapter.entry.LoginBean
import com.spread.xdpnetwork.network.model.response.BaseResponse
import com.spread.xdpnetwork.network.model.response.BlogsResponse
import com.spread.xdpnetwork.network.model.response.FriendsResponse
import com.spread.xdpnetwork.network.model.response.TestLoginResponse
import com.spread.xdpnetwork.network.model.response.ThreadsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginService {
    @POST("/wz/user/login")
    fun login(
        @Body bean : LoginBean
    ): Call<ThreadsResponse>
    @FormUrlEncoded
    @POST("/wz/user/testLogin")
    fun testLogin(
        @Field("stuId") stuId: String,
        @Field("password") password: String
    ): Call<TestLoginResponse>

    @GET("/wz/blog/queryHottestBlog")
    fun queryHottestBlog(@Query("current") current: Int) : Call<BlogsResponse>


    @GET("/wz/blog/queryLikeBlog")
    fun queryLikeBlog(@Query("current") current: Int) : Call<BlogsResponse>

    @GET("/wz/blog/queryNewestBlog")
    fun queryNewestBlog(@Query("current") current: Int) : Call<BlogsResponse>
    @GET("/wz/friend/allFriends")
    fun getAllFriends() : Call<FriendsResponse>

    @GET("/wz/blog/like/{id}")
    fun likeBlog(@Path("id") id:Long):Call<BaseResponse>

    @POST("/wz/blog/searchBlog")
    fun searchBlog(@Query("current") current: Int,@Query("keyword") keyword: String): Call<BlogsResponse>

    @GET("/wz/blog/searchTagWordByTypeId")
    fun searchTagWordByTypeId(@Query("current") current: Int,@Query("typeId") typeId: Int,@Query("keyword") keyword:String): Call<BlogsResponse>
}