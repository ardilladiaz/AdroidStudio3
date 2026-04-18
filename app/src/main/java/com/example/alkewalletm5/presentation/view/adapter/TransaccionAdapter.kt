package com.example.alkewalletm5.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletm5.R
import com.example.alkewalletm5.data.model.Transaccion
import com.squareup.picasso.Picasso

class TransaccionAdapter(): RecyclerView.Adapter<TransaccionAdapter.ViewHolder>() {

    var items = mutableListOf<Transaccion>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imagenYara)
        val nombreReceptor: TextView = view.findViewById(R.id.txtYaraMovimiento)
        val monto: TextView = view.findViewById(R.id.MontoEnvioYara)
        val icono: ImageView = view.findViewById(R.id.IconoEnvioYara)
        val fecha: TextView = view.findViewById(R.id.txtfechaYara)
        val simbolo: TextView = view.findViewById(R.id.simboloPeso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaccion_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[items.size - position - 1]
        Picasso.get().load(item.fotoPerfil).into(holder.imagen)
        holder.nombreReceptor.text = item.idReceriver
        holder.monto.text = item.monto.toString()
        holder.icono.setImageResource((item.icono))
        holder.fecha.text = item.fecha
        holder.simbolo.text = item.simbolo
    }

    override fun getItemCount(): Int {
        return items.size
    }

}