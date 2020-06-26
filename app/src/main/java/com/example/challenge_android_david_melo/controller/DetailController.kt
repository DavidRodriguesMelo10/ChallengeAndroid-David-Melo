import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.example.challenge_android_david_melo.BuildConfig
import com.example.challenge_android_david_melo.model.Hq
import com.example.challenge_android_david_melo.model.Price
import com.example.challenge_android_david_melo.netWebservice.RetrofitInstance
import com.example.challenge_android_david_melo.service.PersonageService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailController {

    fun getHQ(id: Int):ArrayList<Price> {

       // val  hash = (BuildConfig.TS + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toMD5()
        val personaServices = RetrofitInstance.retrofitInstance?.create(PersonageService::class.java)
        val call: Call<Hq>? = personaServices?.getAllHQ(id,  BuildConfig.TS, BuildConfig.PUBLIC_KEY, BuildConfig.MD5)
        val listPrices =  ArrayList<Price>()
        call?.enqueue(object : Callback<Hq> {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onResponse(@NonNull call: Call<Hq>, @NonNull response: Response<Hq>) {
                if (response.isSuccessful) {
                    val hq = response.body()
                    val results = hq?.data?.hqResult

                    for (it in 0 until results?.size!!) {
                        for (x in 0 until results[it].price.size) {
                            listPrices.add(results[it].price[x])
                        }
                    }
                    Log.i("#DetalheController", "Response : $response.body()")

                } else {
                    Log.e("#DetalheController", "Response : $response.errorBody()")
                }
            }
            override fun onFailure(@NonNull call: Call<Hq>, @NonNull t: Throwable) {
                Log.e("#DetalheController", "OnFailure : $t.message")
            }
        })
        return listPrices
    }

}