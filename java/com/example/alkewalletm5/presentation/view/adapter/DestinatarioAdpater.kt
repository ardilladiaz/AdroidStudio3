package com.example.alkewalletm5.presentation.view.adapter
/**
 * Clase Adapter
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Destinatario
/**
 * Adapter para manejar una lista de destinatarios en un Spinner.
 *
 * @param context el contexto actual
 * @param items la lista de destinatarios a mostrar
 */
class DestinatarioAdpater (context: Context, private val items: List<Destinatario>) :
    ArrayAdapter<Destinatario>(context, 0, items) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    /**
     * Obtiene la vista para un elemento en la posición dada.
     *
     * @param position la posición del elemento en el dataset
     * @param convertView la vista reutilizable
     * @param parent el ViewGroup al que esta vista será añadida
     * @return la vista del elemento en la posición dada
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent, R.layout.destinatarios_item)
    }
    /**
     * Obtiene la vista desplegable para un elemento en la posición dada.
     *
     * @param position la posición del elemento en el dataset
     * @param convertView la vista reutilizable
     * @param parent el ViewGroup al que esta vista será añadida
     * @return la vista desplegable del elemento en la posición dada
     */
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent, R.layout.destinatarios_item)
    }
    /**
     * Crea una vista a partir de un recurso.
     *
     * @param position la posición del elemento en el dataset
     * @param convertView la vista reutilizable
     * @param parent el ViewGroup al que esta vista será añadida
     * @param resource el recurso de layout para inflar la vista
     * @return la vista creada a partir del recurso
     */
    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup, resource: Int): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
        val item = getItem(position)

        val imageView = view.findViewById<ImageView>(R.id.imagenPerfilEnviarDinero)
        val nameTextView = view.findViewById<TextView>(R.id.textNombreEnviarDinero)
        val emailTextView = view.findViewById<TextView>(R.id.textEmailEnviarDinero)
    // Si item no es nulo, se ejecuta el bloque dentro de let
        item?.let {
            imageView.setImageResource(it.fotoPerfil)
            nameTextView.text = it.nombre
            emailTextView.text = it.correo
        }

        return view
    }

}