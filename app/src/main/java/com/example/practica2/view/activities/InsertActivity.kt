package com.example.practica2.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica2.R
import com.example.practica2.databinding.ActivityInsertBinding
import com.example.practica2.db.DBCars

class InsertActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInsertBinding
    private lateinit var marca: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val marcasArray = resources.getStringArray(R.array.marcas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, marcasArray)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                marca = marcasArray[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


    }

    fun click(view: View) {
        val dbCars = DBCars(this)
        with(binding){
            when{
                tietModelo.text.toString().isEmpty() -> {
                    tietModelo.error = "No puede quedar vacío"
                    Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }

                tietColor.text.toString().isEmpty() -> {
                    tietModelo.error = "No puede quedar vacío"
                    Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //Realizamos la inserción
                    val id = dbCars.insertCar(tietModelo.text.toString(), marca, tietColor.text.toString())

                    if(id>0){
                        Toast.makeText(this@InsertActivity, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()
                        tietModelo.setText("")
                       // tietMarca.setText("")
                        tietColor.setText("")
                        tietModelo.requestFocus()
                    }else{
                        Toast.makeText(this@InsertActivity, "Error al guardar el registro", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

}