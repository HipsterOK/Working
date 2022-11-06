package ru.porcupine.guessthefruit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Controller(context: Context) {

    private var displayMetrics = context.resources.displayMetrics
    var screenHeight = displayMetrics.heightPixels / displayMetrics.density
    var screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val hpSize = screenWidth/10
    val charSize = screenWidth/8

    val wordsList = listOf("kiwi", "mango", "orange", "grapefruit", "banana", "pomegranate", "peach",
        "apple", "pear", "pineapple", "apricot", "melon", "watermelon", "grapes")

    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    var alphabetAlpha = mutableStateListOf(
        1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f
    )
    var alphabetEnabled = mutableListOf(
        true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
    )

    var hp = 7

    var hpAlpha = mutableStateListOf(
        1f, 1f, 1f, 1f, 1f, 1f, 1f
    )

    val random = (0..13).random()
    val word = wordsList[random]
    val chars = word.toCharArray()

    var charAlpha = mutableStateListOf<Float>()

    var count = 0
    var find = false
    var gameEnabled = true

    var win by mutableStateOf(0f)
    var lose by mutableStateOf(0f)
    var menuBtn by mutableStateOf(false)

    fun fillCharAlpha(){
        for(i in chars.indices){
            charAlpha.add(0f)
        }
    }

}