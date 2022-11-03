package com.example.practica2.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica2.R
import com.example.practica2.databinding.ListElementBinding
import com.example.practica2.model.Car
import com.example.practica2.view.activities.MainActivity


class CarsAdapter(private val context: Context, val cars: ArrayList<Car>): RecyclerView.Adapter<CarsAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(view: ListElementBinding): RecyclerView.ViewHolder(view.root){
        val tvModelo = view.tvModelo
        val tvMarca = view.tvMarca
        val tvColor = view.tvColor
        var ivImage = view.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListElementBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvModelo.text = cars[position].Modelo
        holder.tvMarca.text = cars[position].Marca
        holder.tvColor.text = cars[position].Color

        when(cars[position].Marca){
            "BMW" -> {
                holder.ivImage.setImageResource(R.drawable.bmw)
            }
            "Nissan" -> {
                holder.ivImage.setImageResource(R.drawable.nissan)
            }
            "PEUGEOT" -> {
                holder.ivImage.setImageResource(R.drawable.peugeot)
            }
            "TOYOTA" -> {
                holder.ivImage.setImageResource(R.drawable.peugeot)
            }
            "VOLKSWAGEN" -> {
                holder.ivImage.setImageResource(R.drawable.volkswagen)
            }

        }


        //Para los clicks de cada elemento viewholder

        holder.itemView.setOnClickListener {
            //Manejar el click
            if(context is MainActivity) context.selectedGame(cars[position])
        }

    }

    override fun getItemCount(): Int {
        return cars.size
    }

}
