package com.example.challenge_android_david_melo.constants

class Constants {

    companion object {
        const val URL_BASE= "http://gateway.marvel.com:80/"

        // tratamento error api
        const val NEW_REQUEST = "Deseja tentar novamente ?"
        const val NOT_FOUND = "Não foi possivel localizar o servidor (URL Não encontrada).$NEW_REQUEST"
        const val NOT_AUTHORIZATED = "Não autorizado. $NEW_REQUEST"
        const val ERROR_ACCESS = "Proibido.$NEW_REQUEST"
        const val TIME_OUT = "Erro na TS, $NEW_REQUEST"
        const val ERROR_TS = "Erro na TS, $NEW_REQUEST"
        const val ERROR_REQUEST = "Requisição invalida, $NEW_REQUEST"
        const val INTERNAL_ERROR = "Erro interno servidor,$NEW_REQUEST"
        const val ERROR_NOT_FOUND = "Erro não encontrado, $NEW_REQUEST"
    }
}