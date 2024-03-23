package com.spread.xdplib.adapter.datamanager

open class SingleHolderContext<out T,in A> (private val creator:(A)->T){
    @Volatile
    private var instance :T?=null
    fun getInstance(arg:A) :T =
        instance ?: synchronized(this){
            instance ?: creator(arg)
        }
}

open class SingleHolder<out T> (private val creator:()->T){
    @Volatile
    private var instance :T?=null
    fun getInstance() :T =
        instance ?: synchronized(this){
            instance ?: creator()
        }
}
