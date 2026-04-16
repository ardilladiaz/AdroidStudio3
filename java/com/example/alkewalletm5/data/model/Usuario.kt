package com.example.alkewalletm5.data.model

import com.example.alkewalletm5.R
/**
 *  Clase Destinatario
 *  @author Fabiola Díaz
 *  @since v1.1 24/05/2024
 *
 * Clase de datos que representa un Usuario (quien utiliza la app)
 *
 * @property nombre el nombre del usuario
 * @property apellido el apellido del usuario
 * @property email la correo electrónico del usuario
 * @property password la contraseña del usuario
 * @property imgPerfil el identificador de recurso de la imagen de perfil del usuario
 * @property saldo el saldo de la cuenta del usuario
 * @property transacciones la lista de transacciones asociadas con el usuario
 */
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