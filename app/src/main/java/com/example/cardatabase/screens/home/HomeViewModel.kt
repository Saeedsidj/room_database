package com.example.cardatabase.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardatabase.dataBase.dao.CompanyDao
import com.example.cardatabase.dataBase.entity.CarEntity
import com.example.cardatabase.dataBase.entity.CompanyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val companyDao: CompanyDao) :ViewModel(){

    private val _companies=MutableStateFlow(emptyList<CompanyEntity>())
    val companies :StateFlow<List<CompanyEntity>> = _companies.asStateFlow()

    private val _company=MutableStateFlow(CompanyEntity(name = "", country = ""))
    val company:StateFlow<CompanyEntity> = _company.asStateFlow()

    private val _cars=MutableStateFlow(emptyList<CarEntity>())
    val cars:StateFlow<List<CarEntity>> = _cars.asStateFlow()

    init {
        observeCompanies()
    }
    private fun observeCompanies(){
        viewModelScope.launch(Dispatchers.IO) {
            companyDao.observeCompanies().collect(_companies)
        }
    }

    fun deleteCompany(companyEntity: CompanyEntity){
        viewModelScope.launch(Dispatchers.IO) {
            companyDao.deleteCompany(companyEntity)
        }
    }

    fun insertCompany(){
        viewModelScope.launch {
            companyDao.insertCompany(_company.value)
        }
    }

    fun updateCoName(name:String){
        viewModelScope.launch {
            val newName=_company.value.copy(name = name)
            _company.emit(newName)
        }
    }

    fun updateCoCountry(country:String){
        viewModelScope.launch {
            val newCountry=_company.value.copy(country = country)
            _company.emit(newCountry)
        }

    }

    fun updateCompany(id:Int){
        viewModelScope.launch {
            companyDao.updateCompany(_company.value.copy(id,_company.value.name,_company.value.country))
        }
    }

}