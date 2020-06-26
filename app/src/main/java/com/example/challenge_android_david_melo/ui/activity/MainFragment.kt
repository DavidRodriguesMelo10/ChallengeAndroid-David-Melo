package com.example.challenge_android_david_melo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_android_david_melo.R


class MainFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_fragment)
        if (savedInstanceState == null){


            val ft = supportFragmentManager.beginTransaction()
            val frag1 = DetailFragment()
            ft.add(R.id.layoutFrag,frag1,"HeLLO")
            ft.commit()
        }

    }
}