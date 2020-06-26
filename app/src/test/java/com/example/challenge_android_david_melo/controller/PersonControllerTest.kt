package com.example.challenge_android_david_melo.controller

import android.util.Log
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock



class PersonControllerTest {

    @InjectMocks
    lateinit var controller:PersonController

    @Mock
    lateinit var log:Log


    @Test
    fun getPersonasTest (){ println(controller.getPersonas()) }
}