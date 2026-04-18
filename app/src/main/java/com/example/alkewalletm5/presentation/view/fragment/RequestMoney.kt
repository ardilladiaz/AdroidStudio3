package com.example.alkewalletm5.presentation.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Destinatario
import com.example.alkewalletm5.databinding.FragmentRequestMoneyBinding
import com.example.alkewalletm5.presentation.view.adapter.DestinatarioAdpater
import com.example.alkewalletm5.presentation.viewmodel.DestinatarioViewModel
import com.example.alkewalletm5.presentation.viewmodel.TransaccionViewModel
import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.example.alkewalletm5.data.model.Transaccion
class RequestMoney : Fragment() {

    private var _binding: FragmentRequestMoneyBinding? = null
    private val binding get() = _binding!!
    private val transaccionViewModel: TransaccionViewModel by activityViewModels()
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()
    private val destinatarioViewModel: DestinatarioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVolverRequest.setOnClickListener{findNavController().navigate(R.id.homePage)}

        mostrarListadeDestinatarios()

        cambioColorTextViewMonto()

        binding.btnIngresarDinero.setOnClickListener() {

            val monto = binding.editTextMontoIngresarDinero.text.toString()
            val posicionDestinatarioSeleccionado = binding.spinnerRecibirDinero.selectedItemPosition
            val destinatario = destinatarioViewModel.obtenerDestinatarioSeleccionado(posicionDestinatarioSeleccionado)
            val nota = binding.editTextNota.text.toString()
            val iconoRequest = R.drawable.request_icon2

            if (monto.isBlank()) {
                Toast.makeText(requireContext(), "Por favor ingrese un monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nota.isBlank()) {
                Toast.makeText(requireContext(), "Por favor ingrese una nota", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val montoRecibido: Double = monto.toDouble()

            val saldoMaximo = usuarioViewModel.sumarSaldoUsuario(montoRecibido)
            if (!saldoMaximo) {
                Toast.makeText(requireContext(), "Excede el saldo máximo permitido de $5.000.000", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (destinatario != null) {

                val nuevaTransferencia = Transaccion(
                    fotoPerfil = destinatario.fotoPerfil,
                    idReceriver = destinatario.nombre,
                    monto = montoRecibido,
                    icono = iconoRequest,
                    fecha = obtenerFecha(),
                    simbolo = "+$"
                )

                transaccionViewModel.guardarTransaccionLocal(requireContext(), nuevaTransferencia)

            } else {
                Toast.makeText(requireContext(), "Debe seleccionar un destinatario", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Envío de dinero exitoso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.homePage)}

    }
    fun mostrarListadeDestinatarios(){
        val spinner = binding.spinnerRecibirDinero

        destinatarioViewModel.destinatarios.observe(viewLifecycleOwner) { destinatarios ->
            val adapter = DestinatarioAdpater(requireContext(), destinatarios)
            spinner.adapter = adapter
        }
    }
    fun obtenerFecha(): String{
        val calendar = Calendar.getInstance()

        val dateFormat = SimpleDateFormat("MMM d, hh:mm a", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun cambioColorTextViewMonto(){
        val editTextMonto = binding.editTextMontoIngresarDinero

        editTextMonto.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    editTextMonto.setTextColor(ContextCompat.getColor(requireContext(), R.color.semiblack))
                    editTextMonto.setBackgroundResource(R.drawable.edittext_backgraound_gris)
                } else {
                    editTextMonto.setTextColor(ContextCompat.getColor(requireContext(), R.color.azul))
                    editTextMonto.setBackgroundResource(R.drawable.edittext_background_azul)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}