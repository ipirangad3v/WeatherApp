package com.ipsoft.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ipsoft.weatherapp.presentation.theme.DarkBlue
import com.ipsoft.weatherapp.presentation.theme.WheatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                viewModel.loadWeatherInfo()
            }
        callRequestPermissions()

        setContent {
            WheatherAppTheme {
                viewModel.state.isLoading.let { isLoading ->
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DarkBlue),
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DarkBlue),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            WeatherCart(state = viewModel.state)
                            RetrievePermissionsButton(
                                showButton = viewModel.showRetrievePermissions,
                                onClick = {
                                    callRequestPermissions()
                                },
                            )


                        }
                    }
                }

            }
        }
    }

    private fun callRequestPermissions() {
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

@Composable
fun RetrievePermissionsButton(
    showButton: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showButton) {
        Button(onClick = onClick, modifier = modifier) {
            Text("Retrieve permissions", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WheatherAppTheme {
    }
}