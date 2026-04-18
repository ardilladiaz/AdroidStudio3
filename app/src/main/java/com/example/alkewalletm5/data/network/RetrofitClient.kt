package com.example.alkewalletm5.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://69e2dccf3327837a1552a8ff.mockapi.io/api/v1/"

    val apiService: AlkeWalletApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Traduce el JSON de internet a tu clase Usuario
            .build()
            .create(AlkeWalletApi::class.java)
    }
}