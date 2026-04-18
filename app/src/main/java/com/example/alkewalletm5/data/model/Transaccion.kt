package com.example.alkewalletm5.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

private val room: Any

@Entity(tableName = "tabla_transacciones") // Esto le dice a Room que cree una tabla
data class Transaccion (
    @PrimaryKey(autoGenerate = true) // Esto le dice a Room que genere un ID automático (1, 2, 3...)
    val idRoom: Int = 0,

    val id: String = "",
    val fotoPerfil: Int,
    val monto: Double = 0.0,
    val idSender: String = "Amanda",
    val idReceriver: String = "",
    val fecha: String = "Oct 14 10:24 AM",
    val icono: Int,
    val simbolo: String= "$"
)