package com.example.challenge_android_david_melo.activity

import android.widget.ProgressBar
import com.example.challenge_android_david_melo.R
import com.example.challenge_android_david_melo.ui.activity.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @InjectMocks
    lateinit var activity: MainActivity

    @Mock
    lateinit var context: Ordering.Context

    @Mock
    lateinit var progress: ProgressBar

    @Before
    fun setup() {
        activity::class.java
    }


    @Test
    fun initComponentTest() {
        Assert.assertNotNull(progress)
    }


//    @Test
//    fun getLabelTest() {
//        Mockito.`when`(context.getString(R.string.msg_error_conection)).thenReturn("Error")
//        Assert.assertEquals("Error", context.getString(R.string.msg_error_conection))
//    }
}