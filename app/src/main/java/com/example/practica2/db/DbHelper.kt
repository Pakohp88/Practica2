package com.example.practica2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbHelper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABSE_VERSION) {
    companion object{
        private const val DATABSE_VERSION = 1
        private const val DATABASE_NAME = "carsRegister.db"
        private const val TABLE_GAMES = "cars"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_GAMES(id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT NOT NULL, marca TEXT NOT NULL, color TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE $TABLE_GAMES")
        onCreate(db)
    }
}