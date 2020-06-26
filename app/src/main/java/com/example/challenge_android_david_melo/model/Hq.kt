package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Hq : Serializable {

    @SerializedName("code")
    var code: Int = 0
    @SerializedName("status")
    var status: String = ""
    @SerializedName("etag")
    var etag: String = ""
    @SerializedName("data")
    lateinit var data: HqData
}