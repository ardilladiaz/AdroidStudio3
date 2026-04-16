package com.example.alkewalletm5.data.model
/**
 *  Clase Destinatario
 *  @author Fabiola Díaz
 *  @since v1.1 24/05/2024
 * Clase de datos que representa un destinatario.
 *
 * @property fotoPerfil el identificador de recurso de la foto de perfil del destinatario
 * @property nombre el nombre del destinatario
 * @property correo el correo electrónico del destinatario
 */
data class Destinatario (
    val fotoPerfil: Int,
    val nombre: String,
    val correo: String
) {

}