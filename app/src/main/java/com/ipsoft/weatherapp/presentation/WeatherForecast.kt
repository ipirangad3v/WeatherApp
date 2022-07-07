package com.ipsoft.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipsoft.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(state: WeatherState, modifier: Modifier = Modifier) {

    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Today", fontSize = 20.sp, color = Color.White)
            Spacer(Modifier.height(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    WeatherForecastItem(
                        weatherData = weatherData,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                    )
                }

            })

        }
    }

}

@Composable
fun WeatherForecastItem(weatherData: WeatherData, modifier: Modifier = Modifier) {

    val formattedTime = remember(weatherData.time) {
        weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    Card(modifier = modifier, backgroundColor = Color.Gray) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                formattedTime,
                fontSize = 16.sp,
                color = Color.White
            )
            Image(
                painter = painterResource(id = weatherData.weatherType.iconRes),
                contentDescription = weatherData.weatherType.weatherDesc,
                modifier = Modifier.width(20.dp)
            )
            Text("${weatherData.temperatureCelcius}°C", fontSize = 16.sp, color = Color.White)

        }
    }


}