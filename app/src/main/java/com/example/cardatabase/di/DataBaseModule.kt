package com.example.cardatabase.di

import android.content.Context
import androidx.room.Room
import com.example.cardatabase.dataBase.CompanyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context):CompanyDatabase{
        return Room.databaseBuilder(
            context,
            CompanyDatabase::class.java,
            "company_db")
            .build()
    }

}