package com.example.alkewalletm5.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkewalletm5.data.model.Usuario
import com.example.alkewalletm5.data.network.RetrofitClient
import kotlinx.coroutines.launch

class UsuarioViewModel: ViewModel() {

    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios

    private val _usuarioLogueado = MutableLiveData<Usuario>()
    val usuarioLogueado: LiveData<Usuario> get() = _usuarioLogueado

    fun cargarUsuariosDeInternet(context: Context) {
        viewModelScope.launch {
            try {
                val listaDesdeInternet = RetrofitClient.apiService.obtenerUsuarios()
                _usuarios.value = listaDesdeInternet.toMutableList()

            } catch (e: Exception) {
                _usuarios.value = mutableListOf()
                Toast.makeText(
                    context,
                    "Error de conexión. Verifique su internet y vuelva a intentarlo.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun setUsuarioLogueado(usuario: Usuario) {
        _usuarioLogueado.value = usuario
    }

    fun addUsuario(nombre: String, apellido: String, email: String, password: String) {
        val usuario = Usuario(nombre, apellido, email, password)
        val currentList = _usuarios.value ?: mutableListOf()
        currentList.add(usuario)
        _usuarios.value = currentList

        setUsuarioLogueado(usuario)
    }

    fun autenticarUsuario(email: String, password: String): Usuario? {
        return _usuarios.value?.find { it.email == email && it.password == password }
    }

    private fun actualizarListaUsuarios(usuarioActualizado: Usuario) {
        _usuarios.value = _usuarios.value?.map {
            if (it.email == usuarioActualizado.email) usuarioActualizado else it
        }?.toMutableList()
    }

    fun restarSaldoUsuario(monto: Double): Boolean {
        _usuarioLogueado.value?.let { usuario ->
            val nuevoSaldo = usuario.saldo - monto
            return if (nuevoSaldo >= 0) {
                val usuarioActualizado = usuario.copy(saldo = nuevoSaldo)
                _usuarioLogueado.value = usuarioActualizado

                actualizarListaUsuarios(usuarioActualizado)
                true
            } else {
                false
            }
        }
        return false
    }

    fun sumarSaldoUsuario(monto: Double): Boolean {
        _usuarioLogueado.value?.let { usuario ->
            val nuevoSaldo = usuario.saldo + monto
            return if (nuevoSaldo > 5_000_000) {
                false
            } else {
                val usuarioActualizado = usuario.copy(saldo = nuevoSaldo)
                _usuarioLogueado.value = usuarioActualizado

                actualizarListaUsuarios(usuarioActualizado)
                true
            }
        }
        return false
    }
}