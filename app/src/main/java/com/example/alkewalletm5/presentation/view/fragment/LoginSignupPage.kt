package com.example.alkewalletm5.presentation.view.fragment
/**
 * Clase Fragmento
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alkewalletm5.R
import com.example.alkewalletm5.databinding.FragmentLoginPageBinding
import com.example.alkewalletm5.databinding.FragmentLoginSignupPageBinding
/**
 * Fragmento que representa la página de inicio de sesión y registro.
 */
class LoginSignupPage : Fragment() {

    private var _binding: FragmentLoginSignupPageBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentLoginSignupPageBinding.inflate(inflater, container, false)
        val view = binding.root

        // Manejar el botón de retroceso para salir de la app
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity() // Salir de la aplicación
            }
        })
        return view
    }
    /**
     * Método llamado después de que la vista asociada con el fragmento haya sido creada.
     *
     * @param view La vista retornada por `onCreateView`.
     * @param savedInstanceState Si no es nulo, este fragmento está siendo recreado a partir de un estado previamente guardado.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)
        binding.btnCrearCuenta.setOnClickListener {
            navController.navigate(R.id.signupPage)
        }

        binding.enlaceYaTienesCuenta.setOnClickListener {
            navController.navigate(R.id.loginPage)
        }
    }
    /**
     * Método llamado cuando la vista asociada con el fragmento está siendo destruida.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita fugas de memoria
    }
}