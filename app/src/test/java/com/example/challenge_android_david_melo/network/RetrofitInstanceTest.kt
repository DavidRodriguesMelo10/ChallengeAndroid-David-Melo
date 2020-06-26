package com.example.challenge_android_david_melo.network


import com.example.challenge_android_david_melo.netWebservice.RetrofitInstance
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RetrofitInstanceTest {

    @InjectMocks
    lateinit var service: RetrofitInstance


    @Test
    fun getURLBaseTest(){
        Assert.assertEquals( "http://gateway.marvel.com/",service.retrofitInstance?.baseUrl().toString())
    }


}