package com.spread.xdpnetwork.network.service

import android.widget.Toast
import com.spread.xdplib.adapter.App
import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.datamanager.UserManager
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.entry.BlogBean
import com.spread.xdplib.adapter.entry.LoginBean
import com.spread.xdplib.adapter.entry.MessageBean
import com.spread.xdplib.adapter.entry.MessageFiendBean
import com.spread.xdplib.adapter.entry.PolicyBody
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdplib.adapter.entry.UserVo
import com.spread.xdplib.adapter.utils.MmkvUtil
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.BasicThreadingCallback
import com.spread.xdpnetwork.network.model.response.BaseResponse
import com.spread.xdpnetwork.network.model.response.BlogsResponse
import com.spread.xdpnetwork.network.model.response.ConnectResponse
import com.spread.xdpnetwork.network.model.response.FriendsResponse
import com.spread.xdpnetwork.network.model.response.PolicyResponse
import com.spread.xdpnetwork.network.model.response.TestLoginResponse
import com.spread.xdpnetwork.network.model.response.UserResponse
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File


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
                        if (it.body()!!.code == 1000)
                            callback.invoke(it.body()!!.data)
                        else
                            Toast.makeText(
                                App.instance().applicationContext,
                                it.body()!!.msg,
                                Toast.LENGTH_SHORT
                            ).show()
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
        service.searchBlog(current, msg).enqueue(object :
            BasicThreadingCallback<BlogsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})

    }

    fun searchTagWordByTypeId(
        current: Int,
        type: Int,
        msg: String,
        callback: ((data: List<Blog>) -> Unit)
    ) {
//        val hashMap = HashMap<String,String>()
//        hashMap["string"] = msg
//        val gson = Gson()
//        val json = gson.toJson(hashMap)
//        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        service.searchTagWordByTypeId(current, type, msg).enqueue(object :
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
        service.queryOnesBlog(current, userId).enqueue(object :
            BasicThreadingCallback<BlogsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})
    }

    fun queryOther(userId: Long, callback: ((userDetail: UserDetail) -> Unit)) {
        service.queryOther(userId).enqueue(object : BasicThreadingCallback<UserResponse>({
            if (it.code() == 200) {
                if(it.body()!!.code==1000) {
                    callback.invoke(it.body()!!.data)
                    UserManager.getInstance().saveUserDetail(userDetail = it.body()!!.data)
                } else{
                    Toast.makeText(
                        App.instance().applicationContext,
                        it.body()!!.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }) {})
    }

    fun makeFriend(friendId: Int, message: String, callback: ((msg: String) -> Unit)) {
        service.makeFriend(friendId, message)
            .enqueue(object : BasicThreadingCallback<BaseResponse>({
                if (it.code() == 200) {
                    callback.invoke(it.body()!!.data)
                }
            }) {})
    }

    fun changeFriendAlterName(friendId: Int, message: String, callback: ((msg: String) -> Unit)) {
        service.changeFriendAlterName(friendId, message)
            .enqueue(object : BasicThreadingCallback<BaseResponse>({
                if (it.code() == 200) {
                    callback.invoke(it.body()!!.data)
                }
            }) {})
    }

    fun pubBlog(bean: BlogBean, callback: ((msg: String) -> Unit)) {
        service.pubBlog(bean).enqueue(object : BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }) {})
    }

    fun policy(file: File, callback: ((msg: String) -> Unit)) {
        service.policy().enqueue(object : BasicThreadingCallback<PolicyResponse>({
            if (it.code() == 200) {
                logd("policy ${it.body()!!.data}")
                val data = it.body()!!.data
                val map = HashMap<String, String>()
                map["key"] = data.dir + file.name
                map["policy"] = data.policy
                map["signature"] = data.signature
                map["success_action_status"] = "200"
                map["OSSAccessKeyId"] = data.accessid
                logd("key: ${data.dir + file.name}")
                val params = generateRequestBody(map, file)
                UserManager.getInstance().saveHost(data.host)
                val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("file", file.name, requestBody)
                service.pubFile(data.host, params, body)
                    .enqueue(object : retrofit2.Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {
                                callback.invoke(data.host + "/" + data.dir + file.name)
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            // 处理失败
                            logd("onFailure : $t")
                        }
                    })
            }
        }) {})
    }

    //比如可以这样生成Map<String, RequestBody> requestBodyMap
    //Map<String, String> requestDataMap这里面放置上传数据的键值对。
    private fun generateRequestBody(
        requestDataMap: Map<String, String?>,
        file: File
    ): MutableMap<String, RequestBody> {
        val requestBodyMap: MutableMap<String, RequestBody> = HashMap()
        for (key in requestDataMap.keys) {
            val requestBody = RequestBody.create(
                MultipartBody.FORM,
                (if (requestDataMap[key] == null) "" else requestDataMap[key])!!
            )
            requestBodyMap[key] = requestBody
        }
//        val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//        requestBodyMap["file"] = requestBody
        return requestBodyMap
    }

    fun connect(callback: (data: List<MessageFiendBean>) -> Unit) {
        service.connect().enqueue(object : BasicThreadingCallback<ConnectResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }) {})
    }

    fun sendMessage(bean: MessageBean, callback: ((msg: String) -> Unit)) {
        service.sendMessage(bean).enqueue(object : BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }) {})
    }

    fun sendVerify(phone: String, callback: ((msg: String) -> Unit)) {
        service.sendVerify(phone).enqueue(object : BasicThreadingCallback<BaseResponse>({
            if (it.code() == 200) {
                callback.invoke(it.body()!!.data)
            }
        }) {})
    }

    fun login(stuId: String, password: String, callback: ((msg: String) -> Unit)) {
        val loginBean = LoginBean(stuId, password, "")
        service.login(loginBean).enqueue(object :
            BasicThreadingCallback<BaseResponse>({
                if (it.code() == 200) {
                    callback.invoke(it.body()!!.data)
                }
            }) {})

    }
}