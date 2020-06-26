package com.example.challenge_android_david_melo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class HqData : Serializable{

        @SerializedName("ofsset")
        var offset: Int = 0
        @SerializedName("limit")
        var limit: Int = 0
        @SerializedName("total")
        var total: Int = 0
        @SerializedName("count")
        var count: Int = 0
        @SerializedName("results")
        var hqResult: ArrayList<HqResults>? = null
}