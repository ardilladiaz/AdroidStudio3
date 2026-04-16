package com.example.alkewalletm5.presentation.view.fragment
/**
 * Clase Fragmento
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
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
import com.example.alkewalletm5.databinding.FragmentSendMoneyBinding
import com.example.alkewalletm5.presentation.view.adapter.DestinatarioAdpater
import com.example.alkewalletm5.presentation.viewmodel.DestinatarioViewModel
import com.example.alkewalletm5.presentation.viewmodel.TransaccionViewModel
import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
/**
 * Fragmento que representa la página de envío de dinero.
 */
class SendMoney : Fragment(){

    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get() = _binding!!
    private val transaccionViewModel: TransaccionViewModel by activityViewModels()
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()
    private val destinatarioViewModel: DestinatarioViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Crea y retorna la jerarquía de vistas asociada con el fragmento.
     *
     * @param inflater El LayoutInflater que se puede usar para inflar cualquier vista en el fragmento.
     * @param container Si no es nulo, este es el padre que contiene la vista del fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento está siendo recreado a partir de un
     * estado previamente guardado.
     * @return La vista para la interfaz de usuario del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Método llamado después de que la vista asociada con el fragmento haya sido creada.
     *
     * @param view La vista retornada por `onCreateView`.
     * @param savedInstanceState Si no es nulo, este fragmento está siendo recreado a partir de un
     * estado previamente guardado.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Al presionar la flecha de la aprte superior vuelve al homePage
        binding.volverSendMoney.setOnClickListener{findNavController().navigate(R.id.homePage)}

        mostrarListadeDestinatarios()

        cambioColorTextViewMonto()

        binding.btnEnviarDinero.setOnClickListener() {

            val monto = binding.editTextMontoEnviarDinero.text.toString()
            val destinatario = binding.spinnerEnviarDinero.selectedItem as Destinatario
            val nota = binding.editTextNotaEnviarDinero.text.toString()
            val iconoSend = R.drawable.send_icon_yellow

            //No permite Enviar dinero si no se han llenado los campos
            if (monto.isBlank()) {
                Toast.makeText(requireContext(), "Por favor ingrese un monto", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (nota.isBlank()) {
                Toast.makeText(requireContext(), "Por favor ingrese una nota", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val montoEnviado: Double = monto.toDouble()

            // Verificar si el saldo es suficiente antes de realizar la transacción
            val saldoSuficiente = usuarioViewModel.restarSaldoUsuario(montoEnviado)
            if (!saldoSuficiente) {
                Toast.makeText(
                    requireContext(),
                    "Saldo insuficiente para realizar la transacción",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            //Creamos una nueva transacción
            transaccionViewModel.nuevaTransaccion(
                fotoPerfil = destinatario.fotoPerfil,
                idReceiver = destinatario.nombre,
                monto = montoEnviado,
                icono = iconoSend,
                fecha = obtenerFecha(),
                simbolo = "-$"
            )

            // Añadir la nueva transacción a la lista de transacciones
            transaccionViewModel.addTransaccion()

            Toast.makeText(requireContext(), "Envío de dinero exitoso", Toast.LENGTH_SHORT).show()

            // Navegar de regreso a HomePage
            findNavController().navigate(R.id.homePage)

        }


    }

    /**
     * Obtener la fecha actual y retornarla como un String
     * Se obtiene una instancia de Calendar que representa la fecha y hora actuales.
     * Se crea un objeto SimpleDateFormat, que permite formatear fechas y horas
     * @return La fecha y hora actual formateada como una cadena de texto en el formato "MMM d, hh:mm a"
     */
    fun mostrarListadeDestinatarios(){
        // Lista de elementos para el Spinner
        val spinner = binding.spinnerEnviarDinero

        // Lista de elementos para el Spinner
        destinatarioViewModel.destinatarios.observe(viewLifecycleOwner) { destinatarios ->
            // Adapter para el Spinner
            val adapter = DestinatarioAdpater(requireContext(), destinatarios)
            spinner.adapter = adapter
        }
    }

    /**
     * Obtener la fecha actual y retornarla como un String
     */
    fun obtenerFecha(): String{
        // Obtener la fecha y hora actual
        val calendar = Calendar.getInstance()

        // Formatear la fecha y hora según el formato deseado
        val dateFormat = SimpleDateFormat("MMM d, hh:mm a", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    /**
     * Esta función pinta el editText del monto
     * cuando no se ha ingresado un monto, está en gris
     * Cuando se empieza a escribir se pone verde
     */
    fun cambioColorTextViewMonto(){
        val editTextMonto = binding.editTextMontoEnviarDinero

        // Configurar el TextWatcher para cambiar el color del texto y el borde
        editTextMonto.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementación
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se necesita implementación
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