package com.example.practica2.view.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practica2.R
import com.example.practica2.databinding.ActivityDetailsBinding
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.db.DBCars
import com.example.practica2.model.Car

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsBinding
    private lateinit var dbCars: DBCars
    var car : Car? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
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

                tietModelo.inputType = InputType.TYPE_NULL
                tietMarca.inputType = InputType.TYPE_NULL
                tietColor.inputType = InputType.TYPE_NULL
            }
        }
    }

    fun click(view: View) {
        when(view.id){
            R.id.btnEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("Id", id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete -> {

                AlertDialog.Builder(this)
                    .setTitle("Confirmacion")
                    .setMessage("Estas seguro de eliminar ${ car?.Marca } - ${ car?.Modelo }   ?")
                    .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, wich ->
                        var banderaCorrecto = dbCars.deleteCar(id)
                        if(banderaCorrecto){
                            Toast.makeText(this@DetailsActivity, "Borrado exitoso", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this@DetailsActivity, "Error borrado", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setNegativeButton("Cancela", DialogInterface.OnClickListener { dialog, wich -> })
                    .show()
            }

        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}