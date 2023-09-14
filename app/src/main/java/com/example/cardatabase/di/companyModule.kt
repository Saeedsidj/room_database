package com.example.cardatabase.di

import com.example.cardatabase.dataBase.CompanyDatabase
import com.example.cardatabase.dataBase.dao.CompanyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class companyModule {

    @Provides
    fun provideDao(companyDatabase: CompanyDatabase):CompanyDao{
        return companyDatabase.companyDao()
    }

}