package com.example.alkewalletm5.presentation.view
/**
 * Clase Fragmento
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletm5.R
/**
 * Actividad principal de la aplicación que se lanza al iniciar.
 *
 * Esta actividad es responsable de cargar el diseño principal de la aplicación.
 */
class MainActivity : AppCompatActivity() {
    /**
     * Método llamado cuando la actividad es creada por primera vez.
     *
     * @param savedInstanceState Si no es nulo, este objeto contiene el estado de la actividad guardado previamente.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}