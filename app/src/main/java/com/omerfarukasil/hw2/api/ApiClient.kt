package com.omerfarukasil.hw2.api

import com.omerfarukasil.hw2.util.ConstantsUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLSession

object ApiClient {

    private var retrofit: Retrofit? = null

    val okHttpClient = OkHttpClient.Builder()
        .hostnameVerifier { hostname: String, session: SSLSession -> true }  // Hostname doğrulaması kapatılıyor
        .build()

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(ConstantsUrl.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) //retrofit will understand as a converter GSON converter will be used
                .build()

        return retrofit as Retrofit
    }

}