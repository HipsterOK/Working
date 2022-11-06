package ru.porcupine.cyberflight.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.cyberflight.controllers.GameController
import ru.porcupine.cyberflight.controllers.InstructionController
import ru.porcupine.cyberflight.functions.SharedPreference
import ru.porcupine.cyberflight.ui.elements.Default
import ru.porcupine.cyberflight.ui.elements.HomeUI
import ru.porcupine.cyberflight.ui.elements.InstructionUI

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val instructionController = InstructionController()
        val gameController = GameController(this)

        setContent {
            val record = SharedPreference(this).getValueInt("record")

            setContent {
                Default().Background(this@HomeActivity)
                HomeUI(this@HomeActivity).ButtonPolicy(this)
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    HomeUI(this@HomeActivity).PlayerPlane(gameController)
                    HomeUI(this@HomeActivity).ButtonPlay(this@HomeActivity)
                    HomeUI(this@HomeActivity).ButtonInstruction(instructionController)
                    HomeUI(this@HomeActivity).TextRecord(record = record)
                }
                InstructionUI().Instruction(context = this@HomeActivity,gameController = gameController, instructionController = instructionController)
            }
        }
    }
}