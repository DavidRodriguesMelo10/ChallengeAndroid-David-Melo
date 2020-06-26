package com.example.challenge_android_david_melo.util

import com.example.challenge_android_david_melo.constants.Constants
import com.example.challenge_android_david_melo.model.Price
import com.example.challenge_android_david_melo.ui.utils.Util
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import java.text.DecimalFormat


@RunWith(MockitoJUnitRunner::class)
class UtilTeste {

    @InjectMocks
    internal var util: Util? = null


    @Test
    fun getMagazinePriceTest() {
        var lista = ArrayList<Price>()

        val p1 = Price()
        p1.price = 0.7

        val p2 = Price()
        p2.price = 0.0
        lista.add(p1)
        lista.add(p2)
        Assert.assertEquals(DecimalFormat.getCurrencyInstance().format(p1.price), util!!.getMagazinePrice(lista))
    }

    @Test
    fun getMagazinePriceElseTest(){
        Assert.assertEquals("R$ 0,00",util!!.getMagazinePrice(ArrayList()))
    }

    @Test
    fun getErroHtmlApiTest(){
        Assert.assertEquals(Constants.NOT_FOUND, Util.getErrorHtmlApi(404))
        Assert.assertEquals(Constants.NOT_AUTHORIZATED, Util.getErrorHtmlApi(401))
        Assert.assertEquals(Constants.ERROR_ACCESS, Util.getErrorHtmlApi(403))
        Assert.assertEquals(Constants.ERROR_TS, Util.getErrorHtmlApi(409))
        Assert.assertEquals(Constants.ERROR_REQUEST, Util.getErrorHtmlApi(400))
        Assert.assertEquals(Constants.INTERNAL_ERROR, Util.getErrorHtmlApi(502))
        Assert.assertEquals(Constants.TIME_OUT, Util.getErrorHtmlApi(504))
        Assert.assertEquals(Constants.ERROR_NOT_FOUND, Util.getErrorHtmlApi(0))
    }



}