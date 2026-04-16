package com.example.alkewalletm5.data.model
/**
 *  Clase TRansaccion
 *  @author Fabiola Díaz
 *  @since v1.1 24/05/2024
 *
 * Clase de datos que representa una transacción
 *
 * @property id el identificador único de la transacción
 * @property fotoPerfil el identificador de recurso de la foto de perfil del destinatario
 * @property monto el monto de la transacción
 * @property idSender el nombre del remitente de la transacción
 * @property idReceriver el nombre del receptor de la transacción
 * @property fecha la fecha y hora de la transacción
 * @property icono el identificador de recurso del ícono asociado a la transacción
 * @property simbolo el símbolo de la moneda utilizada en la transacción
 */
data class Transaccion (
    val id: String = "",
    val fotoPerfil: Int,
    val monto: Double = 0.0,
    val idSender: String = "Amanda",
    val idReceriver: String = "",
    val fecha: String = "Oct 14 10:24 AM",
    val icono: Int,
    val simbolo: String= "$"
) {

}