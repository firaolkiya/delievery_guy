package com.example.taskapp.presentation.delevery_guy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskapp.presentation.common.InputField
import com.example.taskapp.presentation.common.NormalButton
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun DeliveryGuyDashboard(){
    val singapore = LatLng(1.35, 103.87)
    val singaporeMarkerState = rememberMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    Scaffold() { innerPadding ->

        LazyColumn (modifier = Modifier.padding(innerPadding)){
            item {
                Column {
                    GoogleMap(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        cameraPositionState = cameraPositionState,
                        uiSettings = com.google.maps.android.compose.MapUiSettings(
                            zoomControlsEnabled = true, // Enables + and - buttons for zooming
                        ),
                        properties = com.google.maps.android.compose.MapProperties(
                            isIndoorEnabled = true,
                            // Allows pinch-to-zoom gestures
                        )
                    ) {
                        Marker(
                            state = singaporeMarkerState,
                            title = "Singapore",
                            snippet = "Marker in Singapore"
                        )
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "Guy is 20km away from you"
                    )

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Contact Guy")

                    }
                    InputField(hint = "Write Something", isPassword = false) {

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    NormalButton(text = "Send") {

                    }

                }
            }
        }
    }
}

