package com.example.challenge_android_david_melo.ui.activity


import android.app.ActivityOptions
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_android_david_melo.BuildConfig.*
import com.example.challenge_android_david_melo.R
import com.example.challenge_android_david_melo.model.Personage
import com.example.challenge_android_david_melo.model.PersonageResults
import com.example.challenge_android_david_melo.netWebservice.RetrofitInstance
import com.example.challenge_android_david_melo.service.PersonageService
import com.example.challenge_android_david_melo.ui.adapter.PersonAdapter
import com.example.challenge_android_david_melo.ui.utils.EndlessRecyclerViewScrollListener
import com.example.challenge_android_david_melo.ui.utils.PersonageClicList
import com.example.challenge_android_david_melo.ui.utils.Util

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress : Dialog
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layout = layout
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        initComponents()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initComponents() {

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadNextDataFromApi(page)
            }
        }
        recyclerView.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

        progress =  indeterminateProgressDialog("Carregando aguarde ... ")
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)
        val call= personaServices?.getAllPersonagens(TS, PUBLIC_KEY, MD5)

        call?.enqueue(object : Callback<Personage> {
            override fun onResponse(@NonNull call: Call<Personage>, @NonNull response: Response<Personage>) {
                progress.dismiss()
                if (response.isSuccessful) {
                    val personaResult = response.body()?.data?.personagemResult
                    recyclerView.adapter = personaResult?.let {
                        PersonAdapter(it, object :
                            PersonageClicList {
                            override fun onClick(personagem: PersonageResults) {
                                val bundle = Bundle()
                                bundle.putSerializable("personagem", personagem)

                                val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, layout, "transition")

                                val intent = Intent(this@MainActivity, DetailPersonageActivity::class.java)
                                intent.putExtras(bundle)
                                startActivityForResult(intent, 1, options.toBundle())

                            }
                        })
                    }
                } else {
                    Util.getErrorHtmlApi(response.code())
                    errorApi( response.message(),Util.getErrorHtmlApi(response.code()))
                    Log.e("#MainActivity", "Response : $response.message()")
                    Log.e("#MainActivity", "Response code : $response.code()")
                }
            }
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onFailure(@NonNull call: Call<Personage>, @NonNull t: Throwable) {
                Log.e("#MainActivity", "OnFailure :" + t.message)
                progress.dismiss()
                if (!Util.isNetwork(applicationContext)) {
                    errorApi(getString(R.string.msg_error_conection), getString(R.string.msg_error_network))
                }

            }
        })

    }


    fun loadNextDataFromApi(offset: Int) {
        Log.i("#MainActivity", "$offset")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun errorApi(title:String, msg:String){
        val builder: AlertDialog.Builder? = this@MainActivity?.let {
            AlertDialog.Builder(it)
        }

        builder?.setMessage(msg)
            ?.setTitle(title)?.setPositiveButton(R.string.msg_sim
            ) { dialog, _ ->
                initComponents()
                dialog.dismiss()
                progress.show()
            }
            ?.setNegativeButton(R.string.msg_nao
            ) { _, _ ->
                finish()
            }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()

    }
}