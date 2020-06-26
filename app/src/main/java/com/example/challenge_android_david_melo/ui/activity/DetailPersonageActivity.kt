package com.example.challenge_android_david_melo.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_android_david_melo.BuildConfig.*
import com.example.challenge_android_david_melo.R
import com.example.challenge_android_david_melo.model.Hq
import com.example.challenge_android_david_melo.model.PersonageResults
import com.example.challenge_android_david_melo.model.Price
import com.example.challenge_android_david_melo.netWebservice.RetrofitInstance
import com.example.challenge_android_david_melo.service.PersonageService
import com.example.challenge_android_david_melo.ui.utils.Util
import kotlinx.android.synthetic.main.activity_detalhe_persona.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailPersonageActivity : AppCompatActivity() {
    private lateinit var personagemHQ: PersonageResults

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_persona)
        var view = this
        initComponents()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initComponents() {
        Thread{
            runOnUiThread{
                val path = "${personagemHQ.thunbnail.patch}.${personagemHQ.thunbnail.extension}"
                Util.getPicture(applicationContext,path,iv_persona_detalhe,progressBar_detalhe)
                getPersonasService(personagemHQ.id)
            }
        }.start()

        val intent = getIntent()
        personagemHQ = getIntent().getSerializableExtra("personagem") as PersonageResults

        if (intent != null) {
            val params = intent.getExtras()
            if (params != null) {
                txt_nome.text = personagemHQ.name
                if (personagemHQ.description.isEmpty()){personagemHQ.description = getString(R.string.msg_description)}
                txt_descricao.text = personagemHQ.description

            }
        }
    }

    private fun getPersonasService(id: Int) {
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)
        val call = personaServices?.getAllHQ(id, TS, PUBLIC_KEY, MD5)
        call?.enqueue(object : Callback<Hq> {
            override fun onResponse(@NonNull call: Call<Hq>, @NonNull response: Response<Hq>) {
                if (response.isSuccessful) {
                    val hq = response.body()
                    val results = hq?.data?.hqResult

                    var listPrices = ArrayList<Price>()

                    for (it in 0 until results?.size!!){
                        for (x in 0 until results[it].price.size){
                            listPrices.add(results[it].price[x])
                        }

                    }

                    personagemHQ.price = listPrices

                    btn_visualizar.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putSerializable("personagem", personagemHQ)

                        val intent = Intent(this@DetailPersonageActivity, DetailActivity::class.java)
                        intent.putExtras(bundle)
                        startActivityForResult(intent, 1)
                        finish()
                    }
                } else {
                    Log.e("#DetalhePersActivity", "Response : " + response.message())
                }
            }
            override fun onFailure(@NonNull call: Call<Hq>, @NonNull t: Throwable) {
                Log.e("#DetalhePersActivity", "OnFailure :" + t.message)
            }
        })
    }
}
