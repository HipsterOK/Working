package ru.porcupine.aztectic_tac.utils

import android.content.Context
import android.content.Intent
import ru.porcupine.aztectic_tac.activities.FinalActivity
import ru.porcupine.aztectic_tac.activities.GameActivity
import ru.porcupine.aztectic_tac.activities.MenuActivity

class Intents {
    fun game(context: Context){
        val game = Intent(context, GameActivity::class.java)
        game.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        game.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        game.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        game.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(game)
    }

    fun final(context: Context, score:Int){
        val final = Intent(context, FinalActivity::class.java)
        final.putExtra("score", score)
        final.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        final.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        final.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        final.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(final)
    }

    fun menu(context: Context){
        val menu = Intent(context, MenuActivity::class.java)
        menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        menu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(menu)
    }
}