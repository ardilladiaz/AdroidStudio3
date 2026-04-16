package com.example.alkewalletm5.data.local
/**
 * Clase DataSet
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Usuario

/**
 * Clase que conteiene una lista de los usuarios registrads. En este caso solo tenemos uno de prueba
 * para poder inicar sesión
 */
class UsuariosDataSet {

    fun ListaUsuarios():MutableList<Usuario>{
        return mutableListOf(
            Usuario("Amanda","Alkemy", "amanda@gmail.com", "amanda123", R.mipmap.amanda, 25000.0),
        )

    }

    fun dataEmpty():MutableList<Usuario>{
        return mutableListOf<Usuario>()
    }
}