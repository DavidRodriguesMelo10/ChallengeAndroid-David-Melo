package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price : Serializable{

    @SerializedName("type")
    var type: String = ""
    @SerializedName("price")
    var price: Double = 0.0

}