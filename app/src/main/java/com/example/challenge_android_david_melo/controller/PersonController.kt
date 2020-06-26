package com.example.challenge_android_david_melo.controller

import android.util.Log
import androidx.annotation.NonNull
import com.example.challenge_android_david_melo.BuildConfig.*
import com.example.challenge_android_david_melo.model.Personage
import com.example.challenge_android_david_melo.netWebservice.RetrofitInstance
import com.example.challenge_android_david_melo.service.PersonageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonController {

    fun getPersonas() {

        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)
        val call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)


        call?.enqueue(object : Callback<Personage> {
            override fun onResponse(@NonNull call: Call<Personage>, @NonNull response: Response<Personage>) {
                if (response.isSuccessful) {
                    val personas = response.body()
                    personas?.data?.personagemResult

                } else {
                    Log.e("#NotSucces", "Response : " + response.message())
                }
            }

            override fun onFailure(@NonNull call: Call<Personage>, @NonNull t: Throwable) {
                Log.e("#Error", "OnFailure :" + t.message)
            }
        })

    }

}