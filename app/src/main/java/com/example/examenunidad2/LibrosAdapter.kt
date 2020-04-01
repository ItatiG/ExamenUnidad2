package com.example.examenunidad2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view_item.view.*

class LibrosAdapter(private val longItemClickListener: (Int) -> Unit) : RecyclerView.Adapter<LibrosAdapter.ViewHolder>()
{
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
    {
        val tvNombre = v.tvNombre
        val tvAutor = v.tvAutor
        val tvGenero = v.tvGenero
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_recycler_view_item, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        viewHolder.itemView.setOnClickListener{
           val intent = Intent(viewHolder.itemView.context, DatosLibrosActivity::class.java)

            intent.putExtra("nombre", Singleton.dataSet.get(position).nombre)
            intent.putExtra("autor", Singleton.dataSet.get(position).autor)
            intent.putExtra("genero", Singleton.dataSet.get(position).genero)

            viewHolder.itemView.context.startActivity(intent)
        }

        viewHolder.itemView.setOnLongClickListener{
            longItemClickListener.invoke(position)
            true
        }

        viewHolder.tvNombre.text = Singleton.dataSet.get(position).nombre
        viewHolder.tvAutor.text = Singleton.dataSet.get(position).autor
        viewHolder.tvGenero.text = Singleton.dataSet.get(position).genero
    }
}