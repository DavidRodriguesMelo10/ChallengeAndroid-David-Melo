package com.example.challenge_android_david_melo.service

import com.example.challenge_android_david_melo.model.Hq
import com.example.challenge_android_david_melo.model.Personage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonageService {

    @GET("/v1/public/characters")
    fun getAllPersonagens(@Query("ts") ts: String,
                          @Query("apikey")apkey:String,
                          @Query("hash")hash:String): Call<Personage>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getAllHQ(@Path("characterId")characterId:Int,
                 @Query("ts") ts: String,
                 @Query("apikey")apkey:String,
                 @Query("hash")hash:String): Call<Hq>

}
