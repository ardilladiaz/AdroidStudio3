package com.example.alkewalletm5

import com.example.alkewalletm5.presentation.viewmodel.UsuarioViewModel
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class UsuarioViewModelTest {

    private lateinit var viewModel: UsuarioViewModel

    @Before
    fun setUp() {
        viewModel = UsuarioViewModel()
    }

    @Test
    fun testAutenticarUsuarioValido() {
        val usuario = viewModel.autenticarUsuario("amanda@gmail.com", "amanda123")

        assertNotNull(usuario)
    }

    @Test
    fun testAutenticarUsuarioInvalido() {
        val usuario = viewModel.autenticarUsuario("falso@gmail.com", "claveIncorrecta")

        assertNull(usuario)
    }
}