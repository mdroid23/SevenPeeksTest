package com.sevenpeakssoftware.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Content {
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("subject")
    @Expose
    var subject: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
}
