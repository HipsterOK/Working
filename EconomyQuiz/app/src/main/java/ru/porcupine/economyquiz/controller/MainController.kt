package ru.porcupine.economyquiz.controller

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.economyquiz.R
import ru.porcupine.economyquiz.adapters.QuestionAdapter
import ru.porcupine.economyquiz.functions.NewIntents
import ru.porcupine.economyquiz.models.QuestionModel
import java.util.*
import kotlin.concurrent.timerTask

class MainController(context: Context) {
    val font = R.font.bowman

    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density

    val padding = screenHeight/45

    var listQuestion:MutableList<QuestionModel> = arrayListOf()
    private var listAnswer:MutableList<String> = arrayListOf()

    private val questionAdapter = QuestionAdapter()

    var question by mutableStateOf("")
    private var trueAnswer by mutableStateOf(value = "")
    var answer1 by mutableStateOf(value = "")
    var answer2 by  mutableStateOf(value = "")
    var answer3 by  mutableStateOf(value = "")
    var answer4 by  mutableStateOf(value = "")
    var id = 0

    var flashAlpha by mutableStateOf(0f)
    var flashBack by mutableStateOf(Color.Green)
    var active = true
    var nextAlpha by mutableStateOf(0f)

    fun fillQuestion(application: Application) {
        listQuestion = questionAdapter.readFromAsset(application)
        listQuestion.shuffle()

        fillAnswers(0)


        answer1 = listAnswer[0]
        answer2 = listAnswer[1]
        answer3 = listAnswer[2]
        answer4 = listAnswer[3]
    }

    private fun fillAnswers(id:Int){
        listAnswer.clear()
        listAnswer.add(listQuestion[id].trueAnswer)
        listAnswer.add(listQuestion[id].wrongAnswer1)
        listAnswer.add(listQuestion[id].wrongAnswer2)
        listAnswer.add(listQuestion[id].wrongAnswer3)

        question = listQuestion[id].question
        trueAnswer = listQuestion[id].trueAnswer

        listAnswer.shuffle()
    }



    fun nextQuestion(){
        if(id<listQuestion.size) {
            id++
        }
        question = listQuestion[id].question
        trueAnswer = listQuestion[id].trueAnswer
        fillAnswers(id)
        answer1 = listAnswer[0]
        answer2 = listAnswer[1]
        answer3 = listAnswer[2]
        answer4 = listAnswer[3]
    }

    var timer by mutableStateOf(30)
    var score by mutableStateOf(0)

    fun startTimer(scope: CoroutineScope, context: Context){
        scope.launch {
            while(timer>0 && id < listQuestion.size-1){
                delay(1000)
                timer--
            }
            Timer().schedule(timerTask {
                NewIntents().newIntentResult(context, score)
            },1000)
        }
    }
}