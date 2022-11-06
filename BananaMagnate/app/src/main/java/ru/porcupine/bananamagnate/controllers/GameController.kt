package ru.porcupine.bananamagnate.controllers

import android.content.Context
import ru.porcupine.bananamagnate.utils.SharedPreference

class GameController(context: Context) {
    var banana = SharedPreference(context).getValueInt("banana")
    var money = SharedPreference(context).getValueInt("money")
    var oneBananaClick = SharedPreference(context).getValueInt("bananaClick")
    var bananaSec = SharedPreference(context).getValueInt("bananaSec")
    var oneBananaPrice = SharedPreference(context).getValueInt("bananaPrice")

    var inShop = false
    var automatic = SharedPreference(context).getValueInt("automatic")

    var priceList = mutableListOf(
        0,
        SharedPreference(context).getValueInt("update2"),
        SharedPreference(context).getValueInt("update3"),
        SharedPreference(context).getValueInt("update4"),
        SharedPreference(context).getValueInt("update5")
    )
}