package com.sklinn.weathermap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sklinn.weathermap.Model.Weather
import com.sklinn.weathermap.Remote.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btnRefresh.setOnClickListener {
//            val cityName = etCity.text.toString()
//            getCityData(cityName)
//        }
        RestClient.getApiService()
            .getWeatherData("yangon", "a675f210ffc552babcfd44a0fe25be64")
            .enqueue(object : Callback<Weather>{
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (response.isSuccessful){
                        response.body()?.let { data ->
                            //populate Ui
//                            populateUi(data)
                        }
                    }
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    //
                }
            })

    }

//    fun populateUi(data: Weather){
////        val icon = data.weather.first().icon
////        Glide.with(this@MainActivity)
////            .load(icon.url)
////            .into(ivWeather)
//        tvWeatherMain.text = data.main.toString()
//        tvWeatherDescription.text = data.weather.first().description
//        lbTempDigit.text = data.main.temp.toString()
//        tvFeelikeTemp.text = data.main.feels_like.toString()
//        tvMinTemp.text = data.main.temp_min.toString()
//        tvMaxTemp.text = data.main.temp_min.toString()
//
//        lbPressureDigit.text = data.main.pressure.toString()
//        lbHumidityDigit.text = data.main.humidity.toString()
//
//    }

    fun getCityData(q:String?){

    }

}