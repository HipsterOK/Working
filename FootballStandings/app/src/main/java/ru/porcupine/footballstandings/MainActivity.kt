package ru.porcupine.footballstandings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ru.porcupine.footballstandings.data.Constants
import ru.porcupine.footballstandings.screens.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val mainViewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)
        mainViewModel.getFixturesToday()
        val constants = Constants(this)
        setContent {
            MainScreen().MainContent(mainViewModel, constants, this)
        }
    }
}