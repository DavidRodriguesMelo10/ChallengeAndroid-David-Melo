@file:Suppress("DEPRECATION")

package com.example.challenge_android_david_melo.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.challenge_android_david_melo.constants.Constants
import com.example.challenge_android_david_melo.model.Price
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.lang.Exception
import java.security.MessageDigest
import java.text.DecimalFormat


object Util {

    // valida conexão internet
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun isNetwork(context: Context): Boolean{
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networks = connection.allNetworks
        return networks.map { connection.getNetworkInfo(it) }
            .any{it.state == NetworkInfo.State.CONNECTED}
    }

    // atualiza  uma imageview
    @RequiresApi(Build.VERSION_CODES.N)
    fun getPicture(context:Context, path:String, imView:ImageView, progressBar:ProgressBar){
        //Picasso.with(itemView.context).load(path).placeholder(R.drawable.placeholder).fit().centerCrop().into(iv_persona)
            Picasso.get()
            .load(path).fit()
            .centerCrop()
            .transform(RoundedCornersTransformation(10, 10))
            .into(imView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    TODO("Not yet implemented")
                }

            })


//        fun String.toMD5():String {
//            val bytes = MessageDigest.getInstance("MD5")
//                .digest(this.toByteArray())
//            return bytes.toHex()
//        }


    }

    // retorna o maior valor de uma lista
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun getMagazinePrice(lista: java.util.ArrayList<Price>): String? {
        var price = lista.stream()
            .mapToDouble { it.price }
            .max()
            .orElse(0.0)
        return DecimalFormat.getCurrencyInstance().format(price)
    }

    //Adiciona o fragment no layout
    fun AppCompatActivity.addFragments(@IdRes layoutId: Int, fragment: Fragment){
        fragment.arguments = intent.extras
        val ft = supportFragmentManager.beginTransaction()
        ft.add(layoutId,fragment)
        ft.commit()
    }

    fun getErrorHtmlApi(code: Int):String {
        when (code) {
            404 -> return Constants.NOT_FOUND
            401 -> return Constants.NOT_AUTHORIZATED
            403 -> return Constants.ERROR_ACCESS
            409 -> return Constants.ERROR_TS
            400 -> return Constants.ERROR_REQUEST
            502 -> return Constants.INTERNAL_ERROR
            504 -> return Constants.TIME_OUT
        }
        return Constants.ERROR_NOT_FOUND
    }
}

//private fun ByteArray.toHex(): String {
//    return joinToString (""){"%02x".format(it)}
//
//}
