package ru.porcupine.mysweets.utils

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.mysweets.R
import ru.porcupine.mysweets.models.EnemyModel
import ru.porcupine.mysweets.models.PlayerModel
import ru.porcupine.mysweets.values.Constants
import ru.porcupine.mysweets.values.Variables

class Controller(private val context: Context) {
    val constants = Constants(context)
    val enemy = EnemyModel(
        mutableStateOf(-constants.enemySize - ((constants.screenWidth-constants.enemySize)/2)),
        false,
        attacked = false,
        constants = constants
    )
    val player = PlayerModel(
        mutableStateOf(R.drawable.mouse),
        true
    )
    val variables = Variables(context)
    val screensController = ScreensController(this)
    val thread1:CoroutineScope = CoroutineScope(Dispatchers.IO)

    private fun checkSee(player: PlayerModel, enemy: EnemyModel){
        if(!player.saveState && enemy.seeState){
            variables.gameEnable = false
        }
    }

    fun runThread(){
        thread1.launch {
            while (variables.gameEnable){
                if(!enemy.attacked){
                    if((0..20).random() == 5){
                        enemy.startAttack()
                    }
                }
                checkSee(player, enemy)
                if(!player.saveState){
                    variables.score++
                }
                delay(100)
            }
            if(variables.score>variables.record){
                variables.record=variables.score
                SharedPreference(context).save("record", variables.record)
            }
            screensController.result()
        }
    }
}