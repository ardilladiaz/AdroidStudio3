package com.example.alkewalletm5

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.alkewalletm5.data.local.AppDatabase
import com.example.alkewalletm5.data.local.TransaccionDao
import com.example.alkewalletm5.data.model.Transaccion
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransaccionDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: TransaccionDao

    @Before
    fun setUp() {
        // Creamos una base de datos temporal que se borrará al terminar
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.transaccionDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertarYLeerTransaccion() = runBlocking {
        val transaccionFalsa = Transaccion(
            fotoPerfil = 1,
            monto = 500.0,
            idSender = "Amanda",
            idReceriver = "Juan",
            fecha = "Hoy",
            icono = 1,
            simbolo = "$"
        )

        dao.insertarTransaccion(transaccionFalsa)

        val historial = dao.obtenerTodasLasTransacciones()

        assertTrue(historial.isNotEmpty())
        assertTrue(historial.first().idReceriver == "Juan")
    }