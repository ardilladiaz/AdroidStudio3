package com.example.alkewalletm5.presentation.view.fragment
/**
 * Clase Fragmento
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkewalletm5.R
import com.example.alkewalletm5.databinding.FragmentHomePageBinding
import com.example.alkewalletm5.presentation.view.adapter.TransaccionAdapter
import com.example.alkewalletm5.presentation.viewmodel.TransaccionViewModel
import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Fragmento que representa la página principal de la aplicación.
 */
class HomePage : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var adapter: TransaccionAdapter
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()
    private val transaccionViewModel: TransaccionViewModel by activityViewModels()
    /**
     * Método llamado para hacer la inicialización de la instancia del Fragment.
     *
     * @param savedInstanceState Si el fragmento está siendo recreado a partir de un estado previamente guardado,
     * este es el estado.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Crea y retorna la jerarquía de vistas asociada con el fragmento.
     *
     * @param inflater El LayoutInflater que se puede usar para inflar cualquier vista en el fragmento.
     * @param container Si no es nulo, este es el padre que contiene la vista del fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento está siendo recreado a partir de un estado previamente guardado.
     * @return La vista para la interfaz de usuario del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        // Manejar el botón de retroceso del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Navegar de vuelta a Fragment1
                //findNavController().navigate(R.id.loginSignupPage)
                mostrarCuadroDialogo()
            }
        })

        return binding.root
    }

    /**
     * Método llamado inmediatamente después de que `onCreateView` ha retornado,
     * pero antes de que cualquier estado guardado haya sido restaurado en la vista.
     *
     * @param view La vista retornada por `onCreateView`.
     * @param savedInstanceState Si no es nulo, este fragmento está siendo recreado a partir de un
     * estado previamente guardado.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)
        //Navegación de lo botones de la aplicación
        binding.btnIngresarDineroHome.setOnClickListener { navController.navigate(R.id.requestMoney) }
        binding.btnEnviarDineroHome.setOnClickListener { navController.navigate(R.id.sendMoney) }
        binding.imagenHomeAmanda.setOnClickListener { navController.navigate(R.id.profilePage) }

        //Intanciamos el adapter
        binding.recyclerTransacciones.layoutManager = LinearLayoutManager(context)
        adapter = TransaccionAdapter()
        binding.recyclerTransacciones.adapter = adapter

        //Actualiza el recyclerview
        transaccionViewModel.transacciones.observe(viewLifecycleOwner) { transacciones ->
            adapter.items = transacciones
            adapter.notifyDataSetChanged()
            updateEmptyState()
        }
        //Actualiza cabecera del home
        usuarioViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            binding.textSaludo.text = "Hola, ${usuario.nombre}"
            binding.textMontoTotal.text = usuario.saldo.toString()
            binding.imagenHomeAmanda.setImageResource(usuario.imgPerfil)
            transaccionViewModel.setListTransactionsData(usuario.transacciones)
        }

        updateEmptyState()
    }

    /**
     * Actualiza el estado vacío de la lista de transacciones.
     * Si hay transacciones muestra la lista de transacciones, sino la vista que dice que no hay
     * transaciones
     */
    private fun updateEmptyState() {
        if (adapter.items.isEmpty()) {
            binding.layoutTransaccionesEmpty.visibility = View.VISIBLE
            binding.recyclerTransacciones.visibility = View.GONE
        } else {
            binding.layoutTransaccionesEmpty.visibility = View.GONE
            binding.recyclerTransacciones.visibility = View.VISIBLE
        }
    }

    private fun mostrarCuadroDialogo() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¿Está seguro que desea salir?")
            .setPositiveButton("Sí") { dialog, id ->
                // Navegar de vuelta a loginSignupPage
                findNavController().navigate(R.id.loginSignupPage)
            }
            .setNegativeButton("No") { dialog, id ->
                // Cerrar el diálogo
                dialog.dismiss()
            }
        // Mostrar el diálogo
        builder.create().show()
    }


}
