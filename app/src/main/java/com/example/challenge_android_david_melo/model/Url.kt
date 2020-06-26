package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Url : Serializable{

    @SerializedName("type")
    var type: String = ""
    @SerializedName("url")
    var url: String = ""
}