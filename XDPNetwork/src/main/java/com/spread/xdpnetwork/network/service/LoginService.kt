package com.spread.xdpnetwork.network.service

import com.spread.xdplib.adapter.entry.BlogBean
import com.spread.xdplib.adapter.entry.LoginBean
import com.spread.xdplib.adapter.entry.MessageFiendBean
import com.spread.xdplib.adapter.entry.PolicyBody
import com.spread.xdpnetwork.network.interceptor.BaseUrl
import com.spread.xdpnetwork.network.model.response.BaseResponse
import com.spread.xdpnetwork.network.model.response.BlogsResponse
import com.spread.xdpnetwork.network.model.response.ConnectResponse
import com.spread.xdpnetwork.network.model.response.FriendsResponse
import com.spread.xdpnetwork.network.model.response.PolicyResponse
import com.spread.xdpnetwork.network.model.response.TestLoginResponse
import com.spread.xdpnetwork.network.model.response.ThreadsResponse
import com.spread.xdpnetwork.network.model.response.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface LoginService {
    @POST("/wz/user/login")
    fun login(
        @Body bean: LoginBean
    ): Call<ThreadsResponse>

    @FormUrlEncoded
    @POST("/wz/user/testLogin")
    fun testLogin(
        @Field("stuId") stuId: String,
        @Field("password") password: String
    ): Call<TestLoginResponse>

    @GET("/wz/blog/queryHottestBlog")
    fun queryHottestBlog(@Query("current") current: Int): Call<BlogsResponse>

    @GET("/wz/blog/queryLikeBlog")
    fun queryLikeBlog(@Query("current") current: Int): Call<BlogsResponse>

    @GET("/wz/blog/queryNewestBlog")
    fun queryNewestBlog(@Query("current") current: Int): Call<BlogsResponse>

    @GET("/wz/friend/allFriends")
    fun getAllFriends(): Call<FriendsResponse>

    @GET("/wz/blog/like/{id}")
    fun likeBlog(@Path("id") id: Long): Call<BaseResponse>

    @POST("/wz/blog/searchBlog")
    fun searchBlog(
        @Query("current") current: Int,
        @Query("keyword") keyword: String
    ): Call<BlogsResponse>

    @GET("/wz/blog/searchTagWordByTypeId")
    fun searchTagWordByTypeId(
        @Query("current") current: Int,
        @Query("typeId") typeId: Int,
        @Query("keyword") keyword: String
    ): Call<BlogsResponse>

    @GET("/wz/blog/queryOnesBlog")
    fun queryOnesBlog(
        @Query("current") current: Int,
        @Query("userId") userId: Long
    ): Call<BlogsResponse>

    @GET("/wz/user/otherUser/{userId}")
    fun queryOther(@Path("userId") userId: Long): Call<UserResponse>

    @POST("/wz/friend/makeFriend")
    fun makeFriend(
        @Query("friendId") friendId: Int,
        @Query("message") message: String
    ): Call<BaseResponse>

    @POST("/wz/friend/changeFriendAlterName")
    fun changeFriendAlterName(
        @Query("friendId") friendId: Int,
        @Query("alterName") alterName: String
    ): Call<BaseResponse>

    @POST("/wz/blog/pubBlog")
    fun pubBlog(@Body bean: BlogBean): Call<BaseResponse>

    @GET("/wz/api/file/oss/policy")
    fun policy(): Call<PolicyResponse>

    @Multipart
    @POST
    fun pubFile(
        @Url url: String,
        @PartMap requestBodyMap: MutableMap<String, RequestBody>,
        @Part file: MultipartBody.Part
    ): Call<ResponseBody>

    @GET("/wz/message/connect")
    fun connect(): Call<ConnectResponse>
}