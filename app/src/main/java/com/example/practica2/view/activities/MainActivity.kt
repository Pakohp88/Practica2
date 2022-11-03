package com.example.practica2.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica2.R
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.db.DBCars
import com.example.practica2.model.Car
import com.example.practica2.view.adapters.CarsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding :  ActivityMainBinding
    private lateinit var  listCars: ArrayList<Car>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbGames = DBCars(this)
        listCars = dbGames.getCars()

        val adapter = CarsAdapter(this, listCars)
        binding.rvGames.layoutManager = LinearLayoutManager(this)
        binding.rvGames.adapter = adapter

        if(listCars.size == 0){
            binding.tvSinRegistros.visibility = View.VISIBLE
        }
        else{
            binding.tvSinRegistros.visibility = View.INVISIBLE
        }
    }

    fun click(view: View) {
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }

    fun selectedGame(car: Car){
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Id", car.id)
        startActivity(intent)
        finish()
    }
}