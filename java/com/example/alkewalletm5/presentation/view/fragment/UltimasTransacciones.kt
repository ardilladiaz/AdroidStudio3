package com.example.alkewalletm5.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alkewalletm5.R

/**
 * Fragmento que representaba las últimas transacciones de la aplicación.
 *
 * @deprecated Esta clase ya no se utiliza y se conservará solo por motivos históricos.
 * Puede eliminarse en futuras versiones. Se utilizó para del demo de la vistas de la aplicación,
 * antes de usar recyclerView para mostrar las últimas transacciones
 */
class UltimasTransacciones : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ultimas_transacciones, container, false)
    }
}