package com.example.alkewalletm5.presentation.view.adapter
/**
 * Clase Adapter
 * @author Fabiola Díaz
 * @since v1.1 24/05/2024
 *
 */
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Transaccion
/**
 * Adapter para manejar una lista de transacciones en un RecyclerView.
 */
class TransaccionAdapter(): RecyclerView.Adapter<TransaccionAdapter.ViewHolder>() {
    /**
     * Lista de elementos de tipo Transaccion que serán mostrados en el RecyclerView.
     */
    var items = mutableListOf<Transaccion>()
    /**
     * ViewHolder que describe una vista de elemento y metadata sobre su lugar dentro del RecyclerView.
     *
     * @param view la vista del elemento
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imagenYara)
        val nombreReceptor: TextView = view.findViewById(R.id.txtYaraMovimiento)
        val monto: TextView = view.findViewById(R.id.MontoEnvioYara)
        val icono: ImageView = view.findViewById(R.id.IconoEnvioYara)
        val fecha: TextView = view.findViewById(R.id.txtfechaYara)
        val simbolo: TextView = view.findViewById(R.id.simboloPeso)
    }

    /**
     * Crea nuevas vistas de elementos (invocadas por el layout manager).
     *
     * @param parent el ViewGroup al que se añadirá la nueva vista después de ser enlazada a una posición del adaptador
     * @param viewType el tipo de vista de la nueva vista
     * @return un nuevo ViewHolder que contiene la vista de tipo Transaccion
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaccion_item, parent, false)
        return ViewHolder(view)
    }
    /**
     * Reemplaza el contenido de una vista de elemento (invocada por el layout manager).
     *
     * @param holder el ViewHolder que será actualizado para representar el contenido del elemento en la posición dada
     * @param position la posición del elemento en el dataset del adaptador
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[items.size - position - 1]
        holder.imagen.setImageResource(item.fotoPerfil)
        holder.nombreReceptor.text = item.idReceriver
        holder.monto.text = item.monto.toString()
        holder.icono.setImageResource((item.icono))
        holder.fecha.text = item.fecha
        holder.simbolo.text = item.simbolo
    }

    /**
     * Devuelve el tamaño del dataset (invocada por el layout manager).
     *
     * @return el tamaño de la lista de items
     */
    override fun getItemCount(): Int {
        return items.size
    }

}