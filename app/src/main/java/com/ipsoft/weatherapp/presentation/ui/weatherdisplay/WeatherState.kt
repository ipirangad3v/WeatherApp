package com.ipsoft.weatherapp.presentation.ui.weatherdisplay

import com.ipsoft.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
