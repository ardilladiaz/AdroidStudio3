package com.example.alkewalletm5.presentation.view.fragment

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
import com.squareup.picasso.Picasso

class HomePage : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var adapter: TransaccionAdapter
    private val usuarioViewModel: UsuarioViewModel by activityViewModels()
    private val transaccionViewModel: TransaccionViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mostrarCuadroDialogo()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usuarioViewModel.cargarUsuariosDeInternet(requireContext())

        val navController = findNavController(view)
        binding.btnIngresarDineroHome.setOnClickListener { navController.navigate(R.id.requestMoney) }
        binding.btnEnviarDineroHome.setOnClickListener { navController.navigate(R.id.sendMoney) }
        binding.imagenHomeAmanda.setOnClickListener { navController.navigate(R.id.profilePage) }

        binding.recyclerTransacciones.layoutManager = LinearLayoutManager(context)
        adapter = TransaccionAdapter()
        binding.recyclerTransacciones.adapter = adapter

        transaccionViewModel.transacciones.observe(viewLifecycleOwner) { transacciones ->
            adapter.items = transacciones
            adapter.notifyDataSetChanged()
            updateEmptyState()
        }
        usuarioViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            binding.textSaludo.text = "Hola, ${usuario.nombre}"
            binding.textMontoTotal.text = usuario.saldo.toString()
            Picasso.get().load(usuario.imgPerfil).into(binding.imagenHomeAmanda)
            transaccionViewModel.setListTransactionsData(usuario.transacciones)
        }

        updateEmptyState()
    }

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
                findNavController().navigate(R.id.loginSignupPage)
            }
            .setNegativeButton("No") { dialog, id ->

                dialog.dismiss()
            }

        builder.create().show()
    }


}
