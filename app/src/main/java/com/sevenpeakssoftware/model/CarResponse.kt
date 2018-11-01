package com.sevenpeakssoftware.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class CarResponse {

    @SerializedName("status")
    @Expose
    public var status: String? = null

    @SerializedName("content")
    @Expose
    public var carsList: ArrayList<Car>? = null
}
