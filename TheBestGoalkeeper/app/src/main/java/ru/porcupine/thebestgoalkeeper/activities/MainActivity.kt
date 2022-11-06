package ru.porcupine.thebestgoalkeeper.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import ru.porcupine.thebestgoalkeeper.*
import ru.porcupine.thebestgoalkeeper.controllers.GameController
import ru.porcupine.thebestgoalkeeper.controllers.kfSpeed
import ru.porcupine.thebestgoalkeeper.controllers.score
import ru.porcupine.thebestgoalkeeper.controllers.timer
import ru.porcupine.thebestgoalkeeper.other.Constants

lateinit var enemyAnimation: AnimationDrawable
lateinit var gameController: GameController
lateinit var constants: Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kfSpeed = 1
        score = 0
        timer = 30

        gameController = GameController()
        constants = Constants(this)

        val enemyImage = mutableListOf<ImageView>()
        val enemyImageId = listOf(
            R.id.enemy_1,
            R.id.enemy_2,
            R.id.enemy_3,
            R.id.enemy_4,
            R.id.enemy_5,
            R.id.enemy_6,
            R.id.enemy_7
        )

        val scoreTxt = findViewById<TextView>(R.id.score)
        val timeTxt = findViewById<TextView>(R.id.time)

        for (i in 0 until 7) {
            enemyImage.add(
                findViewById<ImageView>(enemyImageId[i]).apply {
                    setBackgroundResource(R.drawable.enemy_animation)
                    enemyAnimation = background as AnimationDrawable
                }
            )
            enemyImage[i].layoutParams.height = constants.meteorHeight
            enemyImage[i].layoutParams.width = constants.meteorWidth
            enemyAnimation.start()
            enemyImage[i].setOnClickListener {
                score++
                updateScore(scoreTxt)
                gameController.enemyController.enemyModels[i].spawn()
            }
        }

        gameController.enemyController.enemyModels.forEach {
            it.spawn()
        }
        gameController.enemyController.move(enemyImage, this)
        gameController.start(this@MainActivity, timeTxt)
    }

    fun updateEnemyPos(enemyImages: MutableList<ImageView>) {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            for(i in 0 until 7){
                enemyImages[i].y = gameController.enemyController.enemyModels[i].y
                enemyImages[i].x = gameController.enemyController.enemyModels[i].x
            }
        }
    }

    fun updateTime(timeTxt: TextView) {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            timeTxt.text = "time\n\n$timer"
        }
    }

    private fun updateScore(scoreTxt: TextView) {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            scoreTxt.text = "score\n\n$score"
        }
    }

    fun gameOver(){
        val final = Intent(this, FinalActivity::class.java)
        final.putExtra("score", score)
        startActivity(final)
        finish()
    }
}