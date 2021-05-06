package com.sklinn.weathermap

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sklinn.weathermap.Model.Weather
import com.sklinn.weathermap.Presenter.MainPresenter
import com.sklinn.weathermap.Presenter.MainView
import com.sklinn.weathermap.Remote.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter()

        mainPresenter.registerView(this)
        mainPresenter.getCityData("yangon")

        btnRefresh.setOnClickListener {
            val cityName = etCity.text.toString()
            mainPresenter.getCityData(cityName)
        }
    }

    override fun populateUi(data: Weather) {
        val icon = data.weather.first().icon
        Glide.with(this@MainActivity)
            .load(icon.url)
            .into(ivWeather)
        tvWeatherMain.text = data.weather.first().main
        tvWeatherDescription.text = data.weather.first().description
        lbTempDigit.text = data.main.temp.toString()+"°C"
        tvFeelikeTemp.text = data.main.feels_like.toString()+"°C"
        tvMinTemp.text = data.main.temp_min.toString()+"°C"
        tvMaxTemp.text = data.main.temp_min.toString()+"°C"

        lbPressureDigit.text = data.main.pressure.toString()+" hPa"
        lbHumidityDigit.text = data.main.humidity.toString()+"%"

    }

    override fun showLoading() {
        pgLoading.visibility = View.VISIBLE
        ivWeather.visibility = View.INVISIBLE
        tvWeatherMain.visibility = View.INVISIBLE
        tvWeatherDescription.visibility = View.INVISIBLE
        containerTemperature.visibility = View.INVISIBLE
        containerPressure.visibility = View.INVISIBLE
        containerHumidity.visibility = View.INVISIBLE
    }

    override fun hideloading() {
        pgLoading.visibility = View.GONE
        ivWeather.visibility = View.VISIBLE
        tvWeatherMain.visibility = View.VISIBLE
        tvWeatherDescription.visibility = View.VISIBLE
        containerTemperature.visibility = View.VISIBLE
        containerPressure.visibility = View.VISIBLE
        containerHumidity.visibility = View.VISIBLE
    }

    override fun showCity(city: String) {
        Toast.makeText(this,city,Toast.LENGTH_LONG)
            .show()
    }
}