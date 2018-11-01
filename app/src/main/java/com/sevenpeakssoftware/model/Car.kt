package com.sevenpeakssoftware.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class Car {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("dateTime")
    @Expose
    var dateTime: String? = null
    @SerializedName("tags")
    @Expose
    var tags: List<Any>? = null
    @SerializedName("content")
    @Expose
    var content: List<Content>? = null
    @SerializedName("ingress")
    @Expose
    var ingress: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("created")
    @Expose
    var created: Int? = null
    @SerializedName("changed")
    @Expose
    var changed: Int? = null
}
