package com.example.examenescursada2024

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExamenDao {
    @Query("SELECT * FROM examen_entity")
    fun getAll(): MutableList<Examen>

    @Insert
    fun insert(examenes: Examen)
}