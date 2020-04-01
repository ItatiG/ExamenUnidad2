package com.example.examenunidad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity(), EliminarLibroDialogFragment.EliminarLibroDialogListener
{
    val onLongItemClickListener: (Int) -> Unit = { position -> //Enviar la posición del elemento al que se le dio click
        DialogEliminarLibro(position)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        LoadData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LibrosAdapter(onLongItemClickListener)
    }

    override fun onResume()
    {
        super.onResume()
    }

    private fun LoadData()
    {
        for (x in 0..20)
        {
            Singleton.dataSet.add(Libro("Libro ${x.toString().padStart(1, '0')}",
                "Autor ${if(x%2 == 0) "Stephen King" else "John Katzenbach"}",
                "${if(x%2 == 0) "Terror" else "Terror psicologico"}"))
        }
    }

    private fun DialogEliminarLibro(position: Int)
    {
        val dialog = EliminarLibroDialogFragment(position)
        dialog.show(supportFragmentManager, "EliminarLibroDialogFragment")
    }

    override fun onDialogPositiveClick(position: Int)
    {
        val libro = Singleton.dataSet.get(position)
        Singleton.dataSet.removeAt(position)

       recyclerView.adapter?.notifyDataSetChanged()

        Snackbar.make(recyclerView, "Libro eliminado ${libro.nombre}", Snackbar.LENGTH_SHORT)
            .setAction("Deshacer", {
                Singleton.dataSet.add(position, libro)
                //recyclerView.adapter?.notifyItemInserted(position)
                recyclerView.adapter?.notifyDataSetChanged()
            }).show()
    }

    override fun onDialogNegativeClick(position: Int)
    {
        Toast.makeText(this, "No se eliminó el libro", Toast.LENGTH_SHORT).show()
    }
}
