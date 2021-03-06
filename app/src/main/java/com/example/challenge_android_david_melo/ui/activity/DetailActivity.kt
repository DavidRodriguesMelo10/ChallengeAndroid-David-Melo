package com.example.challenge_android_david_melo.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_android_david_melo.R
import com.example.challenge_android_david_melo.model.PersonageResults
import com.example.challenge_android_david_melo.ui.utils.Util
import kotlinx.android.synthetic.main.activity_detalhe_hq.*


class  DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_hq)
        initComponents()

    }

    private fun initComponents() {
        val intent = intent
        var personagem = getIntent().getSerializableExtra("personagem") as PersonageResults

        if (intent != null) {
            val params = intent.getExtras()
            if (params != null) {
                val price = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Util.getMagazinePrice(personagem.price)
                } else {
                    TODO("VERSION.SDK_INT < N")
                }
                txt_price.text = "${price}"
                txt_nome_hq.text = personagem.name
                if (personagem.description.isEmpty()){personagem.description = getString(R.string.msg_description)}
                txt_descricao_hq.text = personagem.description

                val path = "${personagem.thunbnail.patch}.${personagem.thunbnail.extension}"
                Thread{
                    runOnUiThread{
                        Util.getPicture(applicationContext,path,iv_persona_hq,progressBar_hq)
                    }
                }.start()



            }
        }
    }

}