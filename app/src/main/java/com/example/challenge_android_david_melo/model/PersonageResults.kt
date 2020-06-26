package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PersonageResults  : Serializable {



    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("description")
    var description: String = ""

    @SerializedName("modified")
    var modified: String = ""

    @SerializedName("thumbnail")
    lateinit var thunbnail: Thumb

    @SerializedName("urls")
    lateinit var urls: ArrayList<Url>

    @SerializedName("prices")
    lateinit var price: ArrayList<Price>


}