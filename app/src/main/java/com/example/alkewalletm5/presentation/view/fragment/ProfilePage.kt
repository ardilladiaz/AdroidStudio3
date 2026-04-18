package com.example.alkewalletm5.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.alkewalletm5.R
import com.example.alkewalletm5.databinding.FragmentProfilePageBinding
import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel

class ProfilePage : Fragment() {
    private var _binding: FragmentProfilePageBinding? = null
    private val binding get() = _binding!!
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homePage)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usuarioViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            binding.textNombrePerfil.text = usuario.nombre
            binding.imagenFotoPerfil.setImageResource(usuario.imgPerfil)
        }

        usuarioViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            binding.txtMostrarInformaccion.text = "Nombre: ${usuario.nombre} \n" +
                                                    "Apellido: ${usuario.apellido} \n" +
                                                        "Email: ${usuario.email} \n"
            binding.txtMostrarTarjetas.text = "No tiene tarjetas asociadas"
        }

        //Muestra la información cuando se hace click en el CardView "Mi información"
        binding.tarjetaMiInformacion.setOnClickListener{
            cambiarVisibilidadCard(binding.infoContainerMiInformacion)
        }
        //Muestra la información cuando se hace click en el CardView "Mis tarjetas"
        binding.tarjetaMisTarjetas.setOnClickListener{
            cambiarVisibilidadCard(binding.infoContainerMiMistarjetas)
        }


    }

    /**
     * Cambia la visibilidad del layout que se encuentran bajo los CarsView, para cuando le hagan
     * Click en el CardView se muestre o se oculte
     */
    private fun cambiarVisibilidadCard(view: View) {
        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
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