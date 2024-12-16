package com.omerfarukasil.hw2.api.service

import com.omerfarukasil.hw2.db.Clothes
import retrofit2.Call
import retrofit2.http.GET

interface ClothesService {
    @GET("AFUG")
    fun getManClothes(): Call<List<Clothes>>

    @GET("6TJN")
    fun getManPants(): Call<List<Clothes>>

    @GET("7DYF")
    fun getManShoes(): Call<List<Clothes>>

    @GET("MA97")
    fun getManHats(): Call<List<Clothes>>

    @GET("RSWO")
    fun getWomanClothes(): Call<List<Clothes>>

    @GET("QSW0")
    fun getWomanPants(): Call<List<Clothes>>

    @GET("28VS")
    fun getWomanShoes(): Call<List<Clothes>>

    @GET("2P9S")
    fun getWomanHats(): Call<List<Clothes>>


}