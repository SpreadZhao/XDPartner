package com.spread.xdpbusiness.xdpsearch

import android.util.SparseArray
import com.spread.xdplib.adapter.utils.TestLogger.logd

object PageCurrentDataManager {
    const val newestBlogCurrent =0
    const val likeBlogCurrent = 1
    const val hottestBlogCurrent= 2
    private val sparseArray:SparseArray<Int> = SparseArray(3)
    init {
        initAll()
    }

    private fun put(current:Int):Int{
        val i = sparseArray.get(current)
        sparseArray.put(current,i+1)
        return i+1
    }

    fun get(current:Int):Int{
        val i = sparseArray.get(current)
        logd("get ---$i")
        put(current)
        return i
    }
    fun initAll(){
        sparseArray.put(newestBlogCurrent,1)
        sparseArray.put(likeBlogCurrent,1)
        sparseArray.put(hottestBlogCurrent,1)
    }

}