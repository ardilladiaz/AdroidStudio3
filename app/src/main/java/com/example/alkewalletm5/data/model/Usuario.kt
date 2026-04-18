package com.example.alkewalletm5.data.model

import com.example.alkewalletm5.R

data class Usuario(
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val imgPerfil: Int = R.mipmap.pp_standar,
    val saldo: Double = 1000.0,
    val transacciones: MutableList<Transaccion> = mutableListOf()
) {

}