package com.example.examenunidad2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_datos_libros.*

class DatosLibrosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_libros)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", View.OnClickListener
                {
                    MiMetodo()
                }).show()
        }

        val nombre = intent.extras?.getString("nombre","")
        val autor = intent.extras?.getString("autor", "")
        val genero = intent.extras?.getString("genero", "")

        Toast.makeText(this, "${nombre} ${autor}", Toast.LENGTH_LONG).show()
    }

    private fun MiMetodo()
    {
        Toast.makeText(this, "Se invocó a MiMetodo", Toast.LENGTH_LONG).show()
    }

}
