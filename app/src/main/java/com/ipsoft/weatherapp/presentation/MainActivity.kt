package com.ipsoft.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ipsoft.weatherapp.presentation.theme.WheatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheatherAppTheme {
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WheatherAppTheme {
    }
}