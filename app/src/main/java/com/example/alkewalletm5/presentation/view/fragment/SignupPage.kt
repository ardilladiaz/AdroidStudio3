package com.example.alkewalletm5.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Usuario
import com.example.alkewalletm5.databinding.FragmentSignupPageBinding
import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel

class SignupPage : Fragment() {

    private var _binding: FragmentSignupPageBinding? = null
    private val binding get() = _binding!!
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupPageBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.loginSignupPage)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)
        binding.buttonLoginSignup.setOnClickListener{llenarFormularioSignup()}
        binding.enlaceYaTieneCuentaSignup.setOnClickListener{navController.navigate(R.id.loginPage)}
    }

    private fun llenarFormularioSignup() {
        val nombre = binding.inputNombre.text.toString()
        val apellido = binding.inputApellidoSignup.text.toString()
        val email = binding.inputEmailSignup.text.toString()
        val password = binding.inputPasswordSignup.text.toString()
        val confirmPassword = binding.inputPassword2Signup.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }


        usuarioViewModel.addUsuario(nombre, apellido, email, password)

        Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.homePage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}