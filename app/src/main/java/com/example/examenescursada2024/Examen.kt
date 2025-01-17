package com.example.examenescursada2024

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "examen_entity")
data class Examen(
  @ColumnInfo(name = "materia") var materia: String,
  @ColumnInfo(name = "fecha") var fecha: String
) {
  @PrimaryKey(autoGenerate = true) var id: Int = 0
}