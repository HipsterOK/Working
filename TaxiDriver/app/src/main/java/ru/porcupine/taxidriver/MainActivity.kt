package ru.porcupine.taxidriver

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import ru.porcupine.taxidriver.ui.theme.Shapes


class MainActivity : ComponentActivity(), SensorEventListener {
    private var senSensorManager: SensorManager? = null
    private var senAccelerometer: Sensor? = null

    private lateinit var playerController:PlayerController
    private lateinit var mainController: MainController
    private lateinit var roadController: RoadController
    private lateinit var enemyController: EnemyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerController = PlayerController(context = this@MainActivity)
        mainController = MainController(this@MainActivity)
        roadController = RoadController(this@MainActivity)
        enemyController = EnemyController(this@MainActivity)

        playerController.startControl(mainController)
        roadController.startRoad(mainController)
        enemyController.spawn(mainController, playerController)
        mainController.startTimer()

        senSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager?
        senAccelerometer = senSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        senSensorManager!!.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL)

        setContent {
            MainContent(playerController, mainController, enemyController)
        }
    }

    @Composable
    fun MainContent(
        playerController: PlayerController,
        mainController: MainController,
        enemyController: EnemyController
    ) {
        RoadBackground(roadController)
        Player(playerController)
        Enemyes(enemyController)
        Timer(mainController)
    }

    @Composable
    fun Timer(mainController: MainController){
        Box {
            Image(
                painter = painterResource(id = R.drawable.navig),
                contentDescription = "timer",
                modifier = Modifier
                    .size(width = (mainController.screenWidth/3).dp, height = (mainController.screenHeight/10).dp)
                    .padding(5.dp)
                    .shadow(2.dp, shape = Shapes.medium)
                    .border(BorderStroke(1.dp, Color.Black), shape = Shapes.medium)
                    .align(Alignment.Center)
            )
            Text(
                text = "${mainController.timer}",
                fontWeight = FontWeight.W700,
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(15.dp)

            )
        }
    }

    @Composable
    fun RoadBackground(roadController: RoadController) {
        Image(
            painter = painterResource(id = R.drawable.road),
            contentDescription = "road",
            modifier = Modifier
                .fillMaxSize()
                .absoluteOffset(x = 0.dp, y = roadController.y1.dp),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.road),
            contentDescription = "road",
            modifier = Modifier
                .fillMaxSize()
                .absoluteOffset(x = 0.dp, y = roadController.y2.dp),
            contentScale = ContentScale.FillWidth
        )
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

    @Composable
    fun Enemy(enemyController: EnemyController, id:Int){
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = enemyController.enemyList[id].painter),
                contentDescription = "car$id",
                modifier = Modifier
                    .absoluteOffset(
                        y = enemyController.enemyY[id].dp,
                        x = enemyController.enemyX[id].dp,
                    )
                    .size(
                        width = enemyController.enemyWidth.dp,
                        height = enemyController.enemyHight.dp
                    )
//                    .background(Color.Cyan),

            )
        }
    }

    @Composable
    fun Enemyes(enemyController: EnemyController) {
        Enemy(enemyController, id = 0)
        Enemy(enemyController, id = 1)
        Enemy(enemyController, id = 2)
        Enemy(enemyController, id = 3)
        Enemy(enemyController, id = 4)
        Enemy(enemyController, id = 5)
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