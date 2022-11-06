package ru.porcupine.mouseandcheese.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.porcupine.mouseandcheese.R
import ru.porcupine.mouseandcheese.utils.SharedPreference

class Variables(context: Context) {

    var imageGamePos = mutableStateListOf(
        mutableStateListOf(R.drawable.false_position, R.drawable.false_position, R.drawable.false_position, R.drawable.false_position),
        mutableStateListOf(R.drawable.false_position, R.drawable.false_position, R.drawable.false_position, R.drawable.false_position),
        mutableStateListOf(R.drawable.false_position, R.drawable.false_position, R.drawable.false_position, R.drawable.false_position),
        mutableStateListOf(R.drawable.false_position, R.drawable.false_position, R.drawable.false_position, R.drawable.false_position),
        mutableStateListOf(R.drawable.false_position, R.drawable.false_position, R.drawable.false_position, R.drawable.false_position),
        mutableStateListOf(R.drawable.false_position, R.drawable.false_position, R.drawable.false_position, R.drawable.false_position),
    )

    var checkGamePos = mutableListOf(
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0)
    )

    var score = 10
    var scoreView by mutableStateOf(10)

    var alphaHeart = mutableStateListOf(1f, 1f, 1f)

    var turn = 5

    var hp = 3

    var record by mutableStateOf(SharedPreference(context).getValueInt("record"))
}