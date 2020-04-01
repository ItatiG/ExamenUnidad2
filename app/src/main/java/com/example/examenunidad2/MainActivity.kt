package com.example.examenunidad2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Asignar un menú
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //Manejar elementos de click
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // Handle item selection
        return when (item.itemId)
        {
            R.id.itBuscarLibrerias ->
            {
                //Intent implicita
                MostrarMapaDeLibrerias()
                true
            }
            R.id.itMostrarListado ->
            {
                //Intent explicita
                startActivity(Intent(this, RecyclerViewActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Método para la instent implicita
    fun MostrarMapaDeLibrerias()
    {
        val gmmIntentUri: Uri = Uri.parse("geo:27.4800, -99.5105?z=19&q=librerias")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null)
        {
            startActivity(mapIntent)
        }
    }
}
