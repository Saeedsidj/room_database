package com.example.cardatabase.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cardatabase.dataBase.dao.CompanyDao
import com.example.cardatabase.dataBase.entity.CompanyEntity

@Database(entities = [CompanyEntity::class], version = 1)
abstract class CompanyDatabase :RoomDatabase(){

    abstract fun companyDao():CompanyDao
}