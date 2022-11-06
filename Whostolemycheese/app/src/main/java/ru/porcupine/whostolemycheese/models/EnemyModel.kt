package ru.porcupine.whostolemycheese.models

import androidx.compose.runtime.MutableState
import ru.porcupine.whostolemycheese.values.Constants
import java.util.*
import kotlin.concurrent.timerTask

class EnemyModel(
    var posX: MutableState<Float>,
    var seeState: Boolean,
    var attacked: Boolean,
    private val constants: Constants
) {

    private fun enemyAttention(){
        posX.value = -constants.enemySize/3 - ((constants.screenWidth-constants.enemySize)/2)
    }

    private fun enemyAttack(){
        posX.value = (constants.screenWidth-constants.enemySize)/2- ((constants.screenWidth-constants.enemySize)/2)
        seeState = true
    }

    fun enemyOut(){
        posX.value = -constants.enemySize - ((constants.screenWidth-constants.enemySize)/2)
        seeState = false
    }

    fun startAttack(){
        attacked = true
        enemyAttention()
        Timer().schedule(timerTask {
            enemyAttack()
            Timer().schedule(timerTask {
                enemyOut()
                Timer().schedule(timerTask {
                    attacked = false
                }, 500)
            }, (1000..2000).random().toLong())
        }, (700..3000).random().toLong())
    }
}