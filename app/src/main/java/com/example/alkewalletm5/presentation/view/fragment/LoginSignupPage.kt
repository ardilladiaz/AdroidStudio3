package com.example.alkewalletm5.presentation.view.fragment

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

class LoginSignupPage : Fragment() {

    private var _binding: FragmentLoginSignupPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginSignupPageBinding.inflate(inflater, container, false)
        val view = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity() // Salir de la aplicación
            }
        })
        return view
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}