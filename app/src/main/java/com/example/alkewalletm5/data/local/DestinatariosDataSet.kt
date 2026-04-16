package com.example.alkewalletm5.data.local
/**
 * Clase DataSet
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Destinatario
import com.example.alkewalletm5.data.model.Transaccion

/**
 * Clase que conteine la lista de destinatarios que se mostrará luego en el Spinner
 */
class DestinatariosDataSet {

    fun ListaDestintarios():MutableList<Destinatario>{
        return mutableListOf(
            Destinatario(R.mipmap.pp2, "Yara Khalil", "yara@example.com"),
            Destinatario(R.mipmap.pp1, "Sara Ibrahim", "sara.ibrahim@example.com"),
            Destinatario(R.mipmap.pp3, "Ahmad Ibrahim", "ahmad.ibrahim@example.com"),
            Destinatario(R.mipmap.pp4, "Reem Khaled", "reem_1993@gmail.com"),
            Destinatario(R.mipmap.pp5, "Hiba Saleh", "hiba.saleh@example.com")
        )

    }

    fun dataEmpty():MutableList<Transaccion>{
        return mutableListOf<Transaccion>()
    }
}