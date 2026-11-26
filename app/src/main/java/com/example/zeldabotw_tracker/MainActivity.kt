package com.example.zeldabotw_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.zeldabotw_tracker.navigation.NavigationControl
import com.example.zeldabotw_tracker.ui.theme.ZeldaBOTW_TrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZeldaBOTW_TrackerTheme {
                NavigationControl()
            }
        }
    }
}