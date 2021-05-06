package com.sklinn.weathermap.Presenter

import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.sklinn.weathermap.Model.Weather
import com.sklinn.weathermap.Remote.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter {
    private var view: MainView? = null

    fun registerView(view: MainView){
        this.view = view
    }

    fun getCityData(q: String) {
        view?.showLoading()
        RestClient.getApiService()
            .getWeatherData(q ?: "yangon", "a675f210ffc552babcfd44a0fe25be64")
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            view?.hideloading()
                            //populate Ui
                            view?.populateUi(data)
                            view?.showCity(q)
                        }
                    }
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    view?.showLoading()
                }
            })
    }

}