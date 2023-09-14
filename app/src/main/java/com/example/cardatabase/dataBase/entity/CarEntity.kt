package com.example.cardatabase.dataBase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carName:String,
    val coId:Int,
    )