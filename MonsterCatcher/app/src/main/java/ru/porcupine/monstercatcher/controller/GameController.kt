package ru.porcupine.monstercatcher.controller

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.monstercatcher.R
import ru.porcupine.monstercatcher.data.Constants
import ru.porcupine.monstercatcher.models.BulletModel
import ru.porcupine.monstercatcher.models.EnemyModel
import ru.porcupine.monstercatcher.models.PlayerModel
import ru.porcupine.monstercatcher.utils.SharedPreference
import java.util.*

class GameController(val context: Context) {

    var score by mutableStateOf(0)
    var record by mutableStateOf(SharedPreference(context).getValueInt("record"))
    val constants = Constants(context)
    val playerModel = PlayerModel(constants = constants)
    val enemyModels = listOf(
        EnemyModel(
            constants,
            yPos = mutableStateOf((Random().nextFloat() + 1) * (-constants.screenHeight/2)),
            xPos = mutableStateOf(Random().nextFloat() * (constants.screenWidth - constants.enemySize)),
            image = R.drawable.monster_1
        ),
        EnemyModel(
            constants,
            yPos = mutableStateOf((Random().nextFloat() + 1) * (-constants.screenHeight/2)),
            xPos = mutableStateOf(Random().nextFloat() * (constants.screenWidth - constants.enemySize)),
            image = R.drawable.monster_2
        ),
        EnemyModel(
            constants,
            yPos = mutableStateOf((Random().nextFloat() + 1) * (-constants.screenHeight/2)),
            xPos = mutableStateOf(Random().nextFloat() * (constants.screenWidth - constants.enemySize)),
            image = R.drawable.monster_3
        ),
        EnemyModel(
            constants,
            yPos = mutableStateOf((Random().nextFloat() + 1) * (-constants.screenHeight/2)),
            xPos = mutableStateOf(Random().nextFloat() * (constants.screenWidth - constants.enemySize)),
            image = R.drawable.monster_4
        )
    )

    val screenController = ScreenController(constants)

    val bullets = listOf(
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        ),
        BulletModel(
            constants,
            yPos = mutableStateOf(constants.screenHeight-constants.playerSize),
            xPos = mutableStateOf((playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)),
            playerModel = playerModel,
            enemyModels = enemyModels,
        )
    )

    var game = true

    fun game(){
        var spawnTime = 0
        var idBullet = 0
        CoroutineScope(Dispatchers.IO).launch {
            while(game) {
                for (i in 0 until 4) {
                    enemyModels[i].move(gameController = this@GameController)
                }
                for (i in 0 until 10) {
                    bullets[i].move(gameController = this@GameController)
                }
                spawnTime++
                if(spawnTime>20){
                    spawnTime=0
                    bullets[idBullet].spawn()
                    idBullet++
                    if(idBullet>9){
                        idBullet=0
                    }
                }
                playerModel.moveRight()
                playerModel.moveLeft()
                delay(20)
            }
        }
    }

    fun updateRecord(context: Context){
        if(score>record){
            record= score
            SharedPreference(context).save("record", record)
        }
    }
}