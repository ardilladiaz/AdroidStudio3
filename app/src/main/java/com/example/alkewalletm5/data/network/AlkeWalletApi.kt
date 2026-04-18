package com.example.alkewalletm5.data.network

import com.example.alkewalletm5.data.model.Transaccion
import com.example.alkewalletm5.data.model.Usuario
import retrofit2.http.GET

interface AlkeWalletApi {
    @GET("usuarios")
    suspend fun obtenerUsuarios(): List<Usuario>
    @GET("endpoint_de_las_transacciones")
    suspend fun obtenerTransacciones(): List<Transaccion>
}