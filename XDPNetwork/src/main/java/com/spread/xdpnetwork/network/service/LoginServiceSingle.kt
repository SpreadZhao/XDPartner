package com.spread.xdpnetwork.network.service

import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.entry.BlogBean
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdplib.adapter.entry.UserVo
import com.spread.xdplib.adapter.utils.MmkvUtil
import com.spread.xdpnetwork.network.BasicThreadingCallback
import com.spread.xdpnetwork.network.model.response.BaseResponse
import com.spread.xdpnetwork.network.model.response.BlogsResponse
import com.spread.xdpnetwork.network.model.response.FriendsResponse
import com.spread.xdpnetwork.network.model.response.TestLoginResponse
import com.spread.xdpnetwork.network.model.response.UserResponse


class LoginServiceSingle private constructor() {
    private var service: LoginService = ServiceCreator.create(LoginService::class.java)

    companion object {
        val instance: LoginServiceSingle by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoginServiceSingle()
        }
    }

    fun testLogin(stuId: String, password: String, callback: (() -> Unit)) {
        service.testLogin(stuId, password).enqueue(object :
            BasicThreadingCallback<TestLoginResponse>(
                {
                    if (it.code() == 200) {
                        MmkvUtil.put(MmkvConstant.MMKV_KEY_TOKEN, it.body()!!.data.token)
                        MmkvUtil.put(MmkvConstant.MMKV_KEY_USERID, it.body()!!.data.userId)
                        callback.invoke()
                    }
                }
            ) {})
    }

    fun queryBlogByPosition(position: Int, current: Int, callback: ((data: List<Blog>) -> Unit)) {
        if (position == 2) {
            service.queryHottestBlog(current).enqueue(object :
                BasicThreadingCallback<BlogsResponse>(
                    {
                        if (it.code() == 200) {
                            callback.invoke(it.body()!!.data)
                        }
                    }
                ) {})
        } else if (position == 1) {
            service.queryLikeBlog(current).enqueue(object :
                BasicThreadingCallback<BlogsResponse>(
                    {
                        if (it.code() == 200) {
                            callback.invoke(it.body()!!.data)
                        }
                    }
                ) {})
        } else if (position == 0) {
            service.queryNewestBlog(current).enqueue(object :
                BasicThreadingCallback<BlogsResponse>(
                    {
                        if (it.code() == 200) {
                            callback.invoke(it.body()!!.data)
                        }
                    }
                ) {})
        }

    }

    fun getAllFriends(callback: ((data: List<UserVo>) -> Unit)) {
        service.getAllFriends().enqueue(object :
            BasicThreadingCallback<FriendsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})
    }

    fun likeBlog(id: Long, callback: (msg: String) -> Unit) {
        service.likeBlog(id).enqueue(object : BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }) {})
    }

    fun searchBlog(current: Int, msg: String, callback: ((data: List<Blog>) -> Unit)) {
        service.searchBlog(current,msg).enqueue(object :
            BasicThreadingCallback<BlogsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})

    }

    fun searchTagWordByTypeId(current: Int, type: Int,msg:String, callback: ((data: List<Blog>) -> Unit)) {
//        val hashMap = HashMap<String,String>()
//        hashMap["string"] = msg
//        val gson = Gson()
//        val json = gson.toJson(hashMap)
//        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        service.searchTagWordByTypeId(current,type,msg).enqueue(object :
            BasicThreadingCallback<BlogsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})

    }
    fun queryOnesBlog(current: Int, userId: Long, callback: ((data: List<Blog>) -> Unit)) {
//        val hashMap = HashMap<String,String>()
//        hashMap["string"] = msg
//        val gson = Gson()
//        val json = gson.toJson(hashMap)
//        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        service.queryOnesBlog(current,userId).enqueue(object :
            BasicThreadingCallback<BlogsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})
    }

    fun queryOther(userId: Long,callback: ((userDetail:UserDetail) -> Unit)){
        service.queryOther(userId).enqueue(object :BasicThreadingCallback<UserResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }){})
    }

    fun makeFriend(friendId: Int,message: String,callback:((msg:String) -> Unit)){
        service.makeFriend(friendId, message).enqueue(object :BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }){})
    }

    fun changeFriendAlterName(friendId: Int,message: String,callback:((msg:String) -> Unit)){
        service.changeFriendAlterName(friendId, message).enqueue(object :BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }){})
    }

    fun pubBlog(bean: BlogBean,callback:((msg:String) -> Unit)){
        service.pubBlog(bean).enqueue(object :BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }){})
    }
}