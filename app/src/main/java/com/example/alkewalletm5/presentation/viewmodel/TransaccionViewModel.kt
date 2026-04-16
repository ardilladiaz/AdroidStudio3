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
import com.example.alkewalletm5.data.model.Transaccion

/**
 * ViewModel para gestionar las transacciones en la aplicación.

 * @property transacciones LiveData que expone la lista mutable de transacciones.
 * @property transaccion LiveData que expone una transacción
 */
class TransaccionViewModel: ViewModel() {

    private val _transacciones = MutableLiveData<MutableList<Transaccion>>()
    val transacciones: LiveData<MutableList<Transaccion>> get() = _transacciones

    private val _transaccion = MutableLiveData<Transaccion>()
    val transaccion: LiveData<Transaccion> get() = _transaccion

    /**
     * Crea una nueva transacción con los datos proporcionados y la asigna al LiveData _transaccion.
     *
     * @param fotoPerfil El identificador del recurso de la foto de perfil de destinatario
     * @param idReceiver El nombre del receptor de la transacción.
     * @param monto El monto de la transacción.
     * @param icono El identificador del recurso del icono de la transacción.
     * @param fecha La fecha de la transacción.
     * @param simbolo El símbolo de la moneda utilizada en la transacción.
     */
    fun nuevaTransaccion(fotoPerfil: Int, idReceiver: String, monto: Double, icono: Int, fecha: String, simbolo: String) {
        val nuevaTransaccion = Transaccion(
            fotoPerfil = fotoPerfil,
            idReceriver = idReceiver,
            monto = monto,
            icono = icono,
            fecha = fecha,
            simbolo = simbolo
        )
        // Asignamos la nueva transacción al LiveData _transaccion
        _transaccion.value = nuevaTransaccion
    }


    init {
        // Inicializa con una lista vacía
        _transacciones.value = mutableListOf()
    }

    /**
     * Actualiza la lista de transacciones con los datos proporcionados.
     *
     * @param listaTransacciones La nueva lista de transacciones.
     */
    fun setListTransactionsData(listaTransacciones: MutableList<Transaccion>){
        _transacciones.value = listaTransacciones
    }

    /**
     * Agrega la transacción actual a la lista de transacciones.
     *
     * Verifica si la transacción actual no es nula antes de agregarla a la lista. Luego actualiza el valor
     * de _transacciones con la nueva lista que contiene la transacción agregada.
     */
    fun addTransaccion() {
        // Obtiene el valor actual de _transaccion
        val nuevaTransaccion = _transaccion.value
        // Verifica si la transacción no es nula antes de agregarla a la lista
        nuevaTransaccion?.let {
            // Obtiene la lista actual de transacciones
            val currentList = _transacciones.value ?: mutableListOf()
            // Agrega la nueva transacción a la lista
            currentList.add(it)
            // Actualiza el valor de _transacciones con la nueva lista que contiene la transacción agregada
            _transacciones.value = currentList
        }
    }

}