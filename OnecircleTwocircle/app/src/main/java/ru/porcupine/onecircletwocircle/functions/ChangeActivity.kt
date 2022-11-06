package ru.porcupine.onecircletwocircle.functions

import android.content.Context
import android.content.Intent
import ru.porcupine.onecircletwocircle.activities.MainActivity
import ru.porcupine.onecircletwocircle.activities.MenuActivity
import ru.porcupine.onecircletwocircle.activities.ResultActivity

class ChangeActivity {

    fun newIntentGame(context: Context){
        val gameActivity = Intent(context, MainActivity::class.java)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        gameActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(gameActivity)
    }

    fun newIntentResult(context: Context, score:Int){
        val resultActivity = Intent(context, ResultActivity::class.java)
        resultActivity.putExtra("score", score)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(resultActivity)
    }

//    fun newIntentInfo(context: Context){
//        val infoActivity = Intent(context, InfoActivity::class.java)
////        infoActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
////        infoActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////        infoActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
////        infoActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        context.startActivity(infoActivity)
//    }

    fun newIntentHome(context: Context){
        val homeActivity = Intent(context, MenuActivity::class.java)
//        homeActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(homeActivity)
    }

}