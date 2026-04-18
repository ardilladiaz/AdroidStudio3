package com.example.alkewalletm5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alkewalletm5.data.model.Transaccion

@Dao
interface TransaccionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTransaccion(transaccion: Transaccion)

    @Query("SELECT * FROM tabla_transacciones")
    suspend fun obtenerTodasLasTransacciones(): List<Transaccion>

    @Query("DELETE FROM tabla_transacciones")
    suspend fun borrarTodasLasTransacciones()
}