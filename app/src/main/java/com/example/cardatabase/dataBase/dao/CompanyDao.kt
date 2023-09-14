package com.example.cardatabase.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cardatabase.dataBase.entity.CompanyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(companyEntity: CompanyEntity)

    @Query("SELECT * FROM company")
    fun observeCompanies(): Flow<List<CompanyEntity>>

    @Query("SELECT * FROM company WHERE id =:id")
    fun observeCompany(id:Int):Flow<CompanyEntity>

    @Delete
    suspend fun deleteCompany(companyEntity: CompanyEntity)

    @Update
    suspend fun updateCompany(companyEntity: CompanyEntity)

}
