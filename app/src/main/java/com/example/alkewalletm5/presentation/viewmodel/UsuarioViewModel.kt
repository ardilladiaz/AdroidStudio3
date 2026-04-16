package com.example.alkewalletm5.presentation.viewmodel

/**
 * Clase ViewModel
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewalletm5.data.local.UsuariosDataSet
import com.example.alkewalletm5.data.model.Usuario
/**
 * ViewModel para gestionar los datos de usuarios en la aplicación.

 * @property usuarios LiveData que expone la lista mutable de usuarios.
 * @property usuarioLogueado LiveData que expone el usuario actualmente autenticado.
 */
class UsuarioViewModel: ViewModel() {

    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios

    private val _usuarioLogueado = MutableLiveData<Usuario>()
    val usuarioLogueado: LiveData<Usuario> get() = _usuarioLogueado


    init {
        // Inicializa con una lista vacía o con datos iniciales
        _usuarios.value = UsuariosDataSet().ListaUsuarios()
    }

    /**
     * Establece el usuario logueado actualmente.
     *
     * @param usuario El usuario que ha iniciado sesión.
     */
    fun setUsuarioLogueado(usuario: Usuario) {
        _usuarioLogueado.value = usuario
    }

    /**
     * Agrega un nuevo usuario a la lista de usuarios.
     *
     * @param usuario El nuevo usuario a añadir (Tipo Usuario)
     */
   /* fun addUsuario(usuario: Usuario) {
        // currentList garantizamos que no modificamos la lista directamente en LiveData sin notificar a los observadores.
        val currentList = _usuarios.value ?: mutableListOf()
        currentList.add(usuario)
        _usuarios.value = currentList
    }*/

    fun addUsuario(nombre: String, apellido: String, email: String, password: String) {
        val usuario = Usuario(nombre, apellido, email, password)
        // currentList garantizamos que no modificamos la lista directamente en LiveData sin notificar a los observadores.
        val currentList = _usuarios.value ?: mutableListOf()
        currentList.add(usuario)
        _usuarios.value = currentList

        // Establecer el usuario logueado
        setUsuarioLogueado(usuario)
    }

    /**
     * Autentica un usuario basado en su email y contraseña.
     *
     * @param email El email del usuario (Tipo String)
     * @param password La contraseña del usuario. (Tipo String)
     * @return El usuario autenticado si se encuentra, de lo contrario null.
     */
    fun autenticarUsuario(email: String, password: String): Usuario? {
        return _usuarios.value?.find { it.email == email && it.password == password }
    }

    /**
     * Actualiza la lista de usuarios con la información del usuario actualizado.
     *
     * @param usuarioActualizado El usuario con la información actualizada. (tipo Usuario)
     */
    private fun actualizarListaUsuarios(usuarioActualizado: Usuario) {
        _usuarios.value = _usuarios.value?.map {
            if (it.email == usuarioActualizado.email) usuarioActualizado else it
        }?.toMutableList()
    }

    /**
     * Resta un monto del saldo del usuario logueado actualmente.
     *
     * @param monto El monto a restar. (tipo Double)
     * @return true si la operación es exitosa, false si el saldo resultante sería negativo.
     */
    fun restarSaldoUsuario(monto: Double): Boolean {
        _usuarioLogueado.value?.let { usuario ->
            val nuevoSaldo = usuario.saldo - monto
            return if (nuevoSaldo >= 0) {
                val usuarioActualizado = usuario.copy(saldo = nuevoSaldo)
                _usuarioLogueado.value = usuarioActualizado

                // Actualizar la lista de usuarios
               actualizarListaUsuarios(usuarioActualizado)
                true
            } else {
                false
            }
        }
        return false
    }

    /**
     * Suma un monto al saldo del usuario logueado actualmente.
     *
     * @param monto El monto a sumar.
     * @return true si la operación es exitosa, false si el saldo resultante supera los 5 millones
     */
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