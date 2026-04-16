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
import com.example.alkewalletm5.data.local.DestinatariosDataSet
import com.example.alkewalletm5.data.model.Destinatario
import com.example.alkewalletm5.data.model.Transaccion
/**
 * ViewModel para gestionar las transacciones en la aplicación.

 * @property transacciones LiveData que expone la lista mutable de transacciones.
 */
class DestinatarioViewModel: ViewModel() {

    private val _destinatarios = MutableLiveData<MutableList<Destinatario>>()
    val destinatarios: LiveData<MutableList<Destinatario>> get() = _destinatarios


    init {
        // Inicializa con una lista vacía
        _destinatarios.value = DestinatariosDataSet().ListaDestintarios()
    }
    /**
     * Obtiene el destinatario seleccionado.
     * @param posicion corresponde a la posición de la lista
     * @return devuelve el destinatario en la posición dada si esta es válida, o null si no lo es.
     */
    fun obtenerDestinatarioSeleccionado(posicion: Int): Destinatario? {
        return _destinatarios.value?.getOrNull(posicion)
    }


}