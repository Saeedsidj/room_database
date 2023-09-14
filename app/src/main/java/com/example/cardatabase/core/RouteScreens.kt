package com.example.cardatabase.core

sealed class RouteScreens(val route : String){
    object home : RouteScreens("home")
}
