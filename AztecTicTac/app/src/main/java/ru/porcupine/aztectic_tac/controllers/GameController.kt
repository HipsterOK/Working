package ru.porcupine.aztectic_tac.controllers

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.porcupine.aztectic_tac.R
import ru.porcupine.aztectic_tac.data.Constants
import java.util.*

class GameController(context: Context) {
    val constants = Constants(context)

    var imageCrystal = mutableStateListOf(
        mutableStateListOf(R.drawable.crystal_empty,R.drawable.crystal_empty, R.drawable.crystal_empty),
        mutableStateListOf(R.drawable.crystal_empty,R.drawable.crystal_empty,R.drawable.crystal_empty),
        mutableStateListOf(R.drawable.crystal_empty,R.drawable.crystal_empty,R.drawable.crystal_empty)
        )

    var alphaCharacter = mutableStateListOf(1f, 0.7f)
    var sizeCharacter = mutableStateListOf(constants.sizeCharacter[1], constants.sizeCharacter[0])
    var turnPlayer = true
    var gameEnable = true
    var score by mutableStateOf(0)

    var winPosY by mutableStateOf(constants.screenHeight)
    var winAlpha by mutableStateOf(0f)

    var drawPosY by mutableStateOf(constants.screenHeight)
    var drawAlpha by mutableStateOf(0f)

    private fun initTable() {
        for (row in 0..2) for (col in 0..2) imageCrystal[row][col] = R.drawable.crystal_empty
    }

    fun checkWin(dot: Int): Boolean {
        for (i in 0..2) {
            if (imageCrystal[i][0] == dot && imageCrystal[i][1] == dot && imageCrystal[i][2] == dot || imageCrystal[0][i] == dot && imageCrystal[1][i] == dot && imageCrystal[2][i] == dot) return true
            if (imageCrystal[0][0] == dot && imageCrystal[1][1] == dot && imageCrystal[2][2] == dot || imageCrystal[2][0] == dot && imageCrystal[1][1] == dot && imageCrystal[0][2] == dot) return true
        }
        return false
    }

    fun turnAI() {
        var x: Int
        var y: Int
        do {
            x = Random().nextInt(3)
            y = Random().nextInt(3)
        } while (!isCellValid(x, y))
        imageCrystal[y][x] = R.drawable.crystal_purple
    }

    private fun isCellValid(x: Int, y: Int): Boolean {
        return if (x < 0 || y < 0 || x >= 3 || y >= 3) false else imageCrystal[y][x] == R.drawable.crystal_empty
    }

    fun turnPlayer(y:Int, x:Int) {
        if(isCellValid(x, y)) {
            imageCrystal[y][x] = R.drawable.crystal_red
        }
    }

    fun isTableFull(): Boolean {
        for (row in 0..2) for (col in 0..2) if (imageCrystal[row][col] == R.drawable.crystal_empty) return false
        return true
    }

    fun firstCharacter(){
        alphaCharacter[1] = 0.7f
        sizeCharacter[1] = constants.sizeCharacter[0]
        alphaCharacter[0] = 1f
        sizeCharacter[0] = constants.sizeCharacter[1]
    }

    fun secondCharacter(){
        alphaCharacter[0] = 0.7f
        sizeCharacter[0] = constants.sizeCharacter[0]
        alphaCharacter[1] = 1f
        sizeCharacter[1] = constants.sizeCharacter[1]
    }

    fun restart(){
        initTable()
        firstCharacter()
        turnPlayer = true
        gameEnable = true
        winPosY = constants.screenHeight
        winAlpha = 0f
        drawPosY = constants.screenHeight
        drawAlpha = 0f
    }

//    fun game() {
//        initTable()
//        while (true) {
//            turnHuman()
//            if (checkWin(SIGN_X)) {
//                println("YOU WIN!")
//                break
//            }
//            if (isTableFull()) {
//                println("Sorry, DRAW!")
//                break
//            }
//            turnAI()
//            printTable()
//            if (checkWin(SIGN_O)) {
//                println("AI WIN!")
//                break
//            }
//            if (isTableFull()) {
//                println("Sorry, DRAW!")
//                break
//            }
//        }
//        println("GAME OVER.")
//        printTable()
//    }
}