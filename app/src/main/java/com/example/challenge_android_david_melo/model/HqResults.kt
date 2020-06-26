package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HqResults : Serializable {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("urls")
    lateinit var urls: ArrayList<Url>

    @SerializedName("prices")
    lateinit var price: ArrayList<Price>
}