package com.example.alkewalletm5.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.alkewalletm5.data.local.AppDatabase
import com.example.alkewalletm5.data.model.Transaccion
import com.example.alkewalletm5.data.network.RetrofitClient // <-- Importación agregada para conectarse a internet

class TransaccionViewModel: ViewModel() {

    private val _transacciones = MutableLiveData<List<Transaccion>>()
    val transacciones: LiveData<List<Transaccion>> get() = _transacciones

    fun setListTransactionsData(lista: List<Transaccion>) {
        _transacciones.value = lista
    }

    fun guardarTransaccionLocal(context: Context, transaccion: Transaccion) {
        viewModelScope.launch {
            try {
                val dao = AppDatabase.getDatabase(context).transaccionDao()
                dao.insertarTransaccion(transaccion)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "No se pudo guardar la transacción en el historial local. Intente más tarde.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun cargarTransaccionesLocales(context: Context) {
        viewModelScope.launch {
            try {
                val dao = AppDatabase.getDatabase(context).transaccionDao()
                val historial = dao.obtenerTodasLasTransacciones()
                _transacciones.value = historial
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Error al cargar su historial. Revise el almacenamiento de su dispositivo.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun descargarTransaccionesDeInternet(context: Context) {
        viewModelScope.launch {
            try {
                val listaDesdeInternet = RetrofitClient.apiService.obtenerTransacciones()
                _transacciones.value = listaDesdeInternet
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Error de conexión al cargar las transacciones. Verifique su internet.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun nuevaTransaccion(
        fotoPerfil: Int,
        idReceiver: String,
        monto: Double,
        icono: Int,
        fecha: String,
        simbolo: String
    ) {
    }
}