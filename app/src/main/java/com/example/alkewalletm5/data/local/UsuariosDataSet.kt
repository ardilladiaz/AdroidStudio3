package com.example.alkewalletm5.data.local

import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Usuario

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