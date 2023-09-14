package com.example.cardatabase.dataBase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val name:String,
    val country:String
    )
