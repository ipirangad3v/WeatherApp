package com.ipsoft.weatherapp.data.repository

import com.ipsoft.weatherapp.data.mappers.toWeatherInfo
import com.ipsoft.weatherapp.data.remote.WeatherApi
import com.ipsoft.weatherapp.domain.repository.WeatherRepository
import com.ipsoft.weatherapp.domain.util.Resource
import com.ipsoft.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {

            Resource.Success(
                data = api.getWeatherData(lat, long).toWeatherInfo()
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error occured")
        }

    }
}