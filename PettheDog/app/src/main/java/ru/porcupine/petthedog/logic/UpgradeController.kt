package ru.porcupine.petthedog.logic

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.porcupine.petthedog.tools.SharedPreference

class UpgradeController(context: Context) {
    var alpha by mutableStateOf(0f)
    var posX by mutableStateOf(MainLogic(context).screenHeight)

    var nameUpgradeList = listOf("New toy", "New comb", "New shampoo", "New feed", "Win")
    var nameUpgradeCost = mutableStateListOf(
        SharedPreference(context).getValueInt("price0"),
        SharedPreference(context).getValueInt("price1"),
        SharedPreference(context).getValueInt("price2"),
        SharedPreference(context).getValueInt("price3"),
        SharedPreference(context).getValueInt("price4")
    )

    fun tapUpgrade(id:Int, mainLogic: MainLogic){
        when (id) {
            0 -> {
                if(mainLogic.balance>=nameUpgradeCost[id]) {
                    mainLogic.balance-=nameUpgradeCost[id]
                    mainLogic.oneSecond++
                    nameUpgradeCost[id]= (nameUpgradeCost[id]*1.2).toInt()
                }
            }
            1 -> {
                if(mainLogic.balance>=nameUpgradeCost[id]) {
                    mainLogic.balance-=nameUpgradeCost[id]
                    mainLogic.oneTap++
                    nameUpgradeCost[id]= (nameUpgradeCost[id]*1.2).toInt()
                }
            }
            2 -> {
                if (mainLogic.balance >= nameUpgradeCost[id]) {
                    mainLogic.balance -= nameUpgradeCost[id]
                    mainLogic.oneTap+=5
                    nameUpgradeCost[id] = (nameUpgradeCost[id] * 1.5).toInt()
                }
            }
            3 -> {
                if (mainLogic.balance >= nameUpgradeCost[id]) {
                    mainLogic.balance -= nameUpgradeCost[id]
                    mainLogic.oneTap++
                    mainLogic.oneSecond++
                    nameUpgradeCost[id] = (nameUpgradeCost[id] * 1.3).toInt()
                }
            }
            4 -> {
                if (mainLogic.balance >= nameUpgradeCost[id]) {
                    mainLogic.balance -= nameUpgradeCost[id]
                    mainLogic.gameEnable = false
                }
            }
            else -> {
            }
        }
    }
}