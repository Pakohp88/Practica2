package com.example.practica2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.practica2.model.Car

open class DBCars(private val context: Context): DbHelper(context) {

    fun insertCar(modelo: String, marca: String, color: String): Long{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0

        try {
            val value = ContentValues()
            value.put("modelo", modelo)
            value.put("marca", marca)
            value.put("color", color)
            id = db.insert("cars", null, value)
        }catch (e: Exception){
            println("Error en la bd" + e.toString())
        }
        finally {
            db.close()
        }

        return id
    }

    fun getCars(): ArrayList<Car>{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase
        var listCars = ArrayList<Car>()
        var carTmp: Car? = null
        var cursorCar : Cursor? = null
        cursorCar = db.rawQuery("SELECT * FROM CARS", null)

        if(cursorCar.moveToFirst()){
            do{
                carTmp = Car(cursorCar.getInt(0), cursorCar.getString(1), cursorCar.getString(2), cursorCar.getString(3))
                listCars.add(carTmp)
            }while(cursorCar.moveToNext())
        }
        return listCars
    }

    fun getCar(id: Int): Car?{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var car: Car? = null
        var cursorCars : Cursor? = null

        cursorCars = db.rawQuery("SELECT * FROM CARS WHERE id = $id LIMIT 1", null)

        if(cursorCars.moveToFirst()){
            car = Car(cursorCars.getInt(0), cursorCars.getString(1), cursorCars.getString(2), cursorCars.getString(3))
        }
        cursorCars.close()
        return car
    }

    fun updateCar(id: Int, modelo: String, marca: String, color: String): Boolean{
        var banderaCorrecto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("UPDATE CARS SET modelo = '$modelo', marca = '$marca', color = '$color' WHERE id = $id")
            banderaCorrecto = true
        }
        catch(e: Exception){

        }
        finally {
            db.close()
        }

        return banderaCorrecto
    }

    fun deleteCar(id: Int): Boolean{
        var banderaCorrecto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM CARS WHERE id = '$id'")
            banderaCorrecto = true
        }
        catch (e: Exception){

        }
        finally {
            db.close()
        }
        return banderaCorrecto
    }
}