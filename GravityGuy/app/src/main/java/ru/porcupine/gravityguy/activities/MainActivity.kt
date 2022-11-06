package ru.porcupine.gravityguy.activities

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.gravityguy.Constants
import ru.porcupine.gravityguy.controllers.GameController
import ru.porcupine.gravityguy.R
import ru.porcupine.gravityguy.controllers.speedFall


lateinit var playerAnimation: AnimationDrawable
lateinit var gameController: GameController
lateinit var constants: Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameController = GameController()
        constants = Constants(this)

        val backgroundOne = findViewById<View>(R.id.background_one) as ImageView
        val backgroundTwo = findViewById<View>(R.id.background_two) as ImageView

        val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = 5000L
        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            val height = backgroundOne.height.toFloat()
            val translationY = height * progress
            backgroundOne.translationY = translationY
            backgroundTwo.translationY = translationY - height
        }
        animator.start()

        val playerImage = findViewById<ImageView>(R.id.playerImg).apply {
            setBackgroundResource(R.drawable.player_animation)
            playerAnimation = background as AnimationDrawable
        }
        playerImage.y = (constants.screenHeight- playerAnimation.intrinsicHeight).toFloat()
        playerAnimation.start()

        val enemyImage = mutableListOf<ImageView>()
        val listEnemyImage = listOf(
            R.id.enemy1,
            R.id.enemy2,
            R.id.enemy3,
            R.id.enemy4,
            R.id.enemy5,
        )

        for(i in 0 until 5){
            enemyImage.add(findViewById(listEnemyImage[i]))
        }

        val scoreTxt = findViewById<TextView>(R.id.scoreTxt)

        val changeSideBtn = findViewById<Button>(R.id.changeSideBtn)
        changeSideBtn.setOnClickListener{
            gameController.playerController.changeSide(playerImage, this)
            Log.i("pos", gameController.playerController.playerModel.x.toString())
            Log.i("rotation", gameController.playerController.playerModel.rotationY.toString())
            Log.i("side", gameController.playerController.playerModel.side.toString())
        }

        gameController.enemyController.fall(enemyImage, this, playerImage)
        CoroutineScope(Dispatchers.IO).launch {
            while (gameController.gameEnbl) {
                delay(1000)
                gameController.score++
                if(speedFall <75) {
                    speedFall += 1
                }
                updateScoreUI(scoreTxt)
            }
            val result = Intent(this@MainActivity, ResultActivity::class.java)
            result.putExtra("score", gameController.score)
            startActivity(result)
            finish()
            Log.e("game", "game over")
        }
        updatePlayerUI(playerImage)
        updateEnemyUI(enemyImage)
    }

    private fun updateScoreUI(scoreTxt:TextView) {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            scoreTxt.text = "score: ${gameController.score}"
        }
    }

    fun updatePlayerUI(playerImage:ImageView) {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            playerImage.x = gameController.playerController.playerModel.x
            playerImage.rotationY = gameController.playerController.playerModel.rotationY
        }
    }

    fun updateEnemyUI(enemyImages: MutableList<ImageView>) {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            for(i in 0 until 5){
                enemyImages[i].y = gameController.enemyController.enemyModels[i].y
                enemyImages[i].x = gameController.enemyController.enemyModels[i].x
                enemyImages[i].rotationY = gameController.enemyController.enemyModels[i].rotationY
            }
        }
    }

}