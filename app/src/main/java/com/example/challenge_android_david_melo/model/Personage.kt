package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Personage : Serializable{

    @SerializedName("etag")
    var etag: String = ""
    @SerializedName("data")
    lateinit var data: Data
    @SerializedName("code")
    var code: Int = 0
    @SerializedName("status")
    var status: String = ""

}