package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Thumb : Serializable {


    @SerializedName("path")
    var patch: String = ""
    @SerializedName("extension")
    var extension: String = ""
}