package ru.porcupine.taxidriver

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.taxidriver.ui.theme.TaxiDriverTheme

class InstructionActivity : ComponentActivity(), SensorEventListener {

    private var senSensorManager: SensorManager? = null
    private var senAccelerometer: Sensor? = null

    private lateinit var playerController:PlayerController
    private lateinit var mainController: MainController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerController = PlayerController(context = this@InstructionActivity)
        mainController = MainController(this@InstructionActivity)

        playerController.startControl(mainController)

        senSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager?
        senAccelerometer = senSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        senSensorManager!!.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL)

        var image by mutableStateOf(R.drawable.info1)

        var timer = 0
        GlobalScope.launch {
            while(timer<15){
                delay(1000)
                timer++
                if(timer==5){
                    image = R.drawable.info2
                }
                if(timer==10){
                    image=R.drawable.info3
                }
            }
        }

        setContent {
            Column(Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        val changePage = Intent(this@InstructionActivity, MenuActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.hands),
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally),
                        contentDescription = null,
                    )
                }

                Image(painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier
                        .size((mainController.screenHeight/2).dp)
                        .fillMaxWidth()
                )
            }
            Player(playerController = playerController)
        }
    }

    @Composable
    fun Player(playerController: PlayerController) {
        val xPosAnim by animateDpAsState(playerController.player.dp)
        Box(
            Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.taxi),
                contentDescription = "player",
                modifier = Modifier
                    .size(
                        width = playerController.playerWidth.dp,
                        height = playerController.playerHeight.dp
                    )
                    .absoluteOffset(
                        x = xPosAnim,
                        y = (mainController.screenHeight - playerController.playerHeight).dp
                    )
//                    .background(Color.Cyan)
                    .padding(bottom = 15.dp),
            )
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val mySensor: Sensor = sensorEvent!!.sensor
        if (mySensor.type == Sensor.TYPE_ACCELEROMETER) {
            playerController.move = -sensorEvent.values[0]/2
        }
    }

    override fun onAccuracyChanged(sensorEvent: Sensor?, p1: Int) {
    }

    override fun onPause() {
        super.onPause()
        senSensorManager!!.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        senSensorManager!!.registerListener(
            this,
            senAccelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }
}
