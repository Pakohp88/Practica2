package com.example.practica2.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.practica2.databinding.ActivityEditBinding
import com.example.practica2.db.DBCars
import com.example.practica2.model.Car

class EditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBinding
    private lateinit var dbCars: DBCars
    var car : Car? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle = intent.extras

        if(bundle != null){
            id = bundle.getInt("Id", 0)
        }

        dbCars = DBCars(this)
        car = dbCars.getCar(id)
        car?.let {
            with(binding){
                tietModelo.setText(it.Modelo)
                tietMarca.setText(it.Marca)
                tietColor.setText(it.Color)
            }
        }

    }

    fun click(view: View) {
        with(binding){
            when{
                tietModelo.text.toString().isEmpty() -> {
                    tietModelo.error = "No puede quedar vacío"
                    Toast.makeText(this@EditActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                tietMarca.text.toString().isEmpty() -> {
                    tietMarca.error = "No puede quedar vacío"
                    Toast.makeText(this@EditActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                tietColor.text.toString().isEmpty() -> {
                    tietColor.error = "No puede quedar vacío"
                    Toast.makeText(this@EditActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    val banderaCorrecto = dbCars.updateCar(id, tietModelo.text.toString(), tietMarca.text.toString(), tietColor.text.toString())

                    if(banderaCorrecto){
                        Toast.makeText(this@EditActivity, "Modificacion exitosa", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                        intent.putExtra("Id", id)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@EditActivity, "Error al guardar el registro", Toast.LENGTH_SHORT).show()
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