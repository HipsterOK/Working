package ru.porcupine.trueorfalse

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.trueorfalse.activities.MainActivity
import ru.porcupine.trueorfalse.activities.ResultActivity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class Controller(context: Context, application: Application) {

    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density

    val font = R.font.peach_days
    private var turn = true

    var timer by mutableStateOf(5)

    var gameEnable = true

    var score = mutableListOf<Int>()
    var answer =false
    var textView by mutableStateOf("")

    var turnId = 0
    var questionId = 0
    private val temp = SharedPreference(context).getValueList("playersNames")
    var playerCount = SharedPreference(context).getValueInt("playerCount")
    var playerList = mutableStateListOf<String>()

    var playersView by mutableStateOf("")

    var question = QuestionAdapter().readFromAsset(application)

    fun startGame(){
        temp.forEach {
            playerList.add(it)
            score.add(0)
        }
        question.shuffle()
    }

    fun startQuestion(context: Context, scope: CoroutineScope, mainActivity: MainActivity){
        scope.launch {
            playersView = playerList[turnId]
            textView = question[questionId].question
            answer = true
            while(gameEnable){
                delay(1000)
                if(timer==5){
                    delay(1000)
                }
                    if (timer == 0) {
                        if(questionId==question.size-1){
                            gameEnable=false
                            break
                        } else {
                            turn = false
                            if (answer) {
                                answer = false
                                mainActivity.alphaNext = 1f
                                Timer().schedule(timerTask {
                                    mainActivity.alphaNext = 0f
                                    nextQuest()
                                }, 2000)
                            }
                        }
                    } else {
                        timer--
                    }
            }
                val scoreStr = mutableListOf<String>()
                score.forEach {
                    scoreStr.add(it.toString())

                }
                SharedPreference(context).saveList("playersNames", playerList)
                SharedPreference(context).saveList("score", scoreStr)
                scoreStr.forEach {
                    Log.i("add", it)
                }
                val resultActivity = Intent(context, ResultActivity::class.java)
                resultActivity.putIntegerArrayListExtra("score", ArrayList(score))
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(resultActivity)
        }
    }

    fun nextQuest(){
        if(gameEnable && questionId<question.size-1) {
            turnId++
            questionId++
            if (turnId >= playerCount) {
                turnId = 0
            }
            playersView = playerList[turnId]
            textView = question[questionId].question
            timer = 5
            turn = true
            answer = true
        }
    }
}