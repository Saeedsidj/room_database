package com.example.cardatabase.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cardatabase.dataBase.entity.CarEntity
import com.example.cardatabase.dataBase.entity.CompanyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carEntity: CarEntity)

    @Query("SELECT * FROM cars")
    fun observeCompanies(): Flow<List<CarEntity>>


}