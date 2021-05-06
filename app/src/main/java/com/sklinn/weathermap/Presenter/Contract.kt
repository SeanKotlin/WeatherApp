package com.sklinn.weathermap.Presenter

import com.sklinn.weathermap.Model.Weather

interface MainView{
    fun populateUi(data:Weather)
    fun showLoading()
    fun hideloading()
    fun showCity(city:String)

}