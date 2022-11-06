package ru.porcupine.cyberflight.controllers

import android.content.Context
import android.content.Intent
import ru.porcupine.cyberflight.activities.GameActivity
import ru.porcupine.cyberflight.activities.HomeActivity
import ru.porcupine.cyberflight.activities.ResultActivity

class ActivityController {

    fun activityMain(context: Context){
        val gameActivity = Intent(context, GameActivity::class.java)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(gameActivity)
    }

    fun activityResult(context: Context, score:Int){
        val resultActivity = Intent(context, ResultActivity::class.java)
        resultActivity.putExtra("score", score)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(resultActivity)
    }

    fun activityHome(context: Context){
        val homeActivity = Intent(context, HomeActivity::class.java)
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(homeActivity)
    }

}