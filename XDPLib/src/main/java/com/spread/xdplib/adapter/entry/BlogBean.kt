package com.spread.xdplib.adapter.entry

data class BlogBean(
    val absent: String?,
    val contact: String?,
    val content: String?,
    val highTagId: Int = 1,
    val imageList: List<String> = emptyList(),
    val isAnonymous: Int = 0,
    val location: String?,
    val lowTags: List<String> = emptyList(),
    val title: String?,
    val whenMeet: String?
) {
    constructor(
        absent: String,
        title: String,
        content: String,
        location: String,
        lowTags: List<String>,
        imageList: List<String>
    ) : this(absent, null, content, 1, imageList, 0, location,lowTags,title,null)
}
