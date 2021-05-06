package com.sklinn.weathermap.Remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sklinn.weathermap.Model.Icon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
    companion object {
        fun getApiService(): ApiService {
            return getRetrofit().create(ApiService::class.java)
        }

        private fun getClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .build()
        }

        private fun getConvterter(): GsonConverterFactory {
            val gson = GsonBuilder()
                .registerTypeAdapter(Icon::class.java, IconDeserializer())
                .create()
            return GsonConverterFactory.create(gson)
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .client(getClient())
                .addConverterFactory(getConvterter())
                .build()
        }
    }
}