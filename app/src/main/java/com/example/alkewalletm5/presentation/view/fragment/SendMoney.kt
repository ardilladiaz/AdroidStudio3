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
import com.example.alkewalletm5.data.model.Transaccion
import com.example.alkewalletm5.databinding.FragmentSendMoneyBinding
import com.example.alkewalletm5.presentation.view.adapter.DestinatarioAdpater
import com.example.alkewalletm5.presentation.viewmodel.DestinatarioViewModel
import com.example.alkewalletm5.presentation.viewmodel.TransaccionViewModel
import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SendMoney : Fragment(){

    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get() = _binding!!
    private val transaccionViewModel: TransaccionViewModel by activityViewModels()
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()
    private val destinatarioViewModel: DestinatarioViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.volverSendMoney.setOnClickListener{findNavController().navigate(R.id.homePage)}

        mostrarListadeDestinatarios()

        cambioColorTextViewMonto()
        binding.btnEnviarDinero.setOnClickListener {

            val monto = binding.editTextMontoEnviarDinero.text.toString()
            val destinatario = binding.spinnerEnviarDinero.selectedItem as Destinatario
            val nota = binding.editTextNotaEnviarDinero.text.toString()
            val iconoSend = R.drawable.send_icon_yellow

            if (monto.isBlank()) {
                Toast.makeText(requireContext(), "Por favor ingrese un monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nota.isBlank()) {
                Toast.makeText(requireContext(), "Por favor ingrese una nota", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val montoEnviado: Double = monto.toDouble()

            val saldoSuficiente = usuarioViewModel.restarSaldoUsuario(montoEnviado)
            if (!saldoSuficiente) {
                Toast.makeText(requireContext(), "Saldo insuficiente para realizar la transacción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevaTransferencia = Transaccion(
                fotoPerfil = destinatario.fotoPerfil,
                idReceriver = destinatario.nombre,
                monto = montoEnviado,
                icono = iconoSend,
                fecha = obtenerFecha(),
                simbolo = "-$"
            )

            transaccionViewModel.guardarTransaccionLocal(requireContext(), nuevaTransferencia)

            Toast.makeText(requireContext(), "Envío de dinero exitoso", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.homePage)
        }


    }

    fun mostrarListadeDestinatarios(){
        val spinner = binding.spinnerEnviarDinero

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
        val editTextMonto = binding.editTextMontoEnviarDinero

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
                    editTextMonto.setTextColor(ContextCompat.getColor(requireContext(), R.color.verde))
                    editTextMonto.setBackgroundResource(R.drawable.edittextt_background_verde)
                }
            }
        })
    }

}