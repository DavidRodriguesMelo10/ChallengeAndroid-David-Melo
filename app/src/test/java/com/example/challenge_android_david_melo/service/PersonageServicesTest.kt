package com.example.challenge_android_david_melo.service

import com.example.challenge_android_david_melo.BuildConfig.*
import com.example.challenge_android_david_melo.netWebservice.RetrofitInstance
import org.junit.Assert
import org.junit.Before
import org.junit.ComparisonFailure
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PersonageServicesTest {

    private val urlChater = "http://gateway.marvel.com/v1/public/characters?ts=1593097639484&apikey=f93ac8ea0436d8cfa53513fc5d05200b&hash=a057df709b86994c54aa1836e43d9eea"
    private val urlComics = "http://gateway.marvel.com/v1/public/characters/10077/comics?ts=1593097639484&apikey=f93ac8ea0436d8cfa53513fc5d05200b&hash=a057df709b86994c54aa1836e43d9eea"
    private lateinit var  personaServices: PersonageService


    @Before
    fun setup() {
        personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)!!
    }

    @Test
    fun validaUrlRequestTest() {
        var call = personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)
        Assert.assertEquals(urlChater, call?.request()?.url().toString())
    }
    @Test
    fun validaUrlRequestHQTest() {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)
        var call = personaServices?.getAllHQ(10077, TS, PUBLIC_KEY, MD5)
        Assert.assertEquals(urlComics, call?.request()?.url().toString())
    }

    @Test (expected = ComparisonFailure::class)
    fun validaUrlRequestErrorHQTest() {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)
        var call = personaServices?.getAllHQ(1, TS, PUBLIC_KEY, MD5)
        Assert.assertEquals(urlComics, call?.request()?.url().toString())
    }
}


