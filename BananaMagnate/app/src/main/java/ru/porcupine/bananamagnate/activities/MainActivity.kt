package ru.porcupine.bananamagnate.activities

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.bananamagnate.controllers.GameController
import ru.porcupine.bananamagnate.adapters.ListUpdateAdapter
import ru.porcupine.bananamagnate.R
import ru.porcupine.bananamagnate.models.UpdatesModel
import ru.porcupine.bananamagnate.utils.SharedPreference
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {

    private var gameController: GameController? = null
    private var bananaText: TextView? = null
    private var coinText: TextView? = null
    private var customAdapter: ListUpdateAdapter? = null
    private var listViewUpdate:ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameController = GameController(this)

        val shopLayout = findViewById<View>(R.id.shopLayout)
        listViewUpdate = findViewById(R.id.listUpdate)

        val updatesModelArrayList = populateList()
        customAdapter = ListUpdateAdapter(this, updatesModelArrayList)
        listViewUpdate!!.adapter = customAdapter
        listViewUpdate!!.onItemClickListener = OnItemClickListener {
                _, _, position, _ ->
            updatesModelArrayList[position].listener!!.invoke()
        }

        bananaText = findViewById(R.id.bananaText)
        coinText = findViewById(R.id.coinText)
        val palm = findViewById<ImageView>(R.id.palm)
        val shopBtn = findViewById<Button>(R.id.shopButton)

        updateCounts()

        palm.setOnClickListener {
            gameController!!.banana+=gameController!!.oneBananaClick
            updateCounts()
            it.rotation = 5f
            Timer().schedule(timerTask {
                it.rotation = 0f
            }, 200)
        }

        shopBtn.setOnClickListener {
            if (gameController!!.inShop) {
                shopLayout.translationX = 10000f
                palm.translationX = 0f
                shopBtn.text = resources.getText(R.string.go_shop)
                gameController!!.inShop = false
            } else {
                shopLayout.translationX = 0f
                palm.translationX = 10000f
                shopBtn.text = resources.getText(R.string.back)
                gameController!!.inShop = true
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(1000L)
                gameController!!.banana += gameController!!.bananaSec
                if(gameController!!.automatic==1){
                    gameController!!.money += gameController!!.banana * gameController!!.oneBananaPrice
                    gameController!!.banana = 0
                }
                updateUI()
            }
        }
    }

    private fun populateList(): ArrayList<UpdatesModel> {

        val list = ArrayList<UpdatesModel>()
        val listUpdate= listOf(
            "Sell bananas",
            "Increase the price of bananas",
            "Improve the number of bananas with one click",
            "Automatic banana extraction",
            "Automatic sale",
        )
        val k = if(gameController!!.automatic==0){
            0
        } else 1
        val j = if(gameController!!.automatic==0){
            5
        } else 4
        for (i in k until j) {
            val imageModel = UpdatesModel(listUpdate[i], gameController?.priceList!![i], actionListUpdate(i))
            list.add(imageModel)
        }

        return list
    }

    private fun actionListUpdate(i:Int): () -> Unit {
        when (i) {
            0 -> return {
                    gameController!!.money += gameController!!.banana * gameController!!.oneBananaPrice
                    gameController!!.banana = 0
                    updateCounts()
            }
            1 -> {
                 return {
                     if (gameController!!.money >= gameController!!.priceList[i]) {
                         gameController!!.money -= gameController!!.priceList[i]
                         gameController!!.priceList[i] =
                             (gameController!!.priceList[i] * 1.5).ceilRound()
                         gameController!!.oneBananaPrice =
                             (gameController!!.oneBananaPrice * 1.2).ceilRound()
                         updateCounts()
                         customAdapter= ListUpdateAdapter(this, populateList())
                         listViewUpdate!!.adapter=customAdapter
                     }
                 }
            }
            2 -> {
                return {
                    if (gameController!!.money >= gameController!!.priceList[i]) {
                        gameController!!.money -= gameController!!.priceList[i]
                        gameController!!.priceList[i] =
                            (gameController!!.priceList[i] * 1.5).ceilRound()
                        gameController!!.oneBananaClick =
                            (gameController!!.oneBananaClick * 1.2).ceilRound()
                        updateCounts()
                        customAdapter= ListUpdateAdapter(this, populateList())
                        listViewUpdate!!.adapter=customAdapter
                    }
                }
            }
            3 -> {
                return {
                    if (gameController!!.money >= gameController!!.priceList[i]) {
                        gameController!!.money -= gameController!!.priceList[i]
                        gameController!!.priceList[i] =
                            (gameController!!.priceList[i] * 1.5).ceilRound()
                        if(gameController!!.bananaSec>0) {
                            gameController!!.bananaSec =
                                (gameController!!.bananaSec * 1.2).ceilRound()
                        } else gameController!!.bananaSec +=1
                        updateCounts()
                        customAdapter= ListUpdateAdapter(this, populateList())
                        listViewUpdate!!.adapter=customAdapter
                    }
                }
            }
            4 -> {
                return {
                    if (gameController!!.money >= gameController!!.priceList[i]) {
                        gameController!!.money -= gameController!!.priceList[i]
                        gameController!!.automatic = 1
                        updateCounts()
                        customAdapter= ListUpdateAdapter(this, populateList())
                        listViewUpdate!!.adapter=customAdapter
                    }
                }
            }
            else -> {
               return {}
            }
        }
        return {}
    }

    private fun updateCounts(){
        bananaText!!.text = gameController!!.banana.toString()
        coinText!!.text = gameController!!.money.toString()
    }

    private fun updateUI() {
        val handler = Handler(baseContext.mainLooper)
        handler.post {
            bananaText!!.text = gameController!!.banana.toString()
            coinText!!.text = gameController!!.money.toString()
        }
    }

    private fun Double.ceilRound():Int{
        return DecimalFormat("#").apply {
            roundingMode = RoundingMode.CEILING
        }.format(this).toInt()
    }

    override fun onPause() {
        super.onPause()

        SharedPreference(this).save("banana", gameController!!.banana)
        SharedPreference(this).save("money", gameController!!.money)
        SharedPreference(this).save("bananaClick", gameController!!.oneBananaClick)
        SharedPreference(this).save("bananaSec", gameController!!.bananaSec)
        SharedPreference(this).save("bananaPrice", gameController!!.oneBananaPrice)
        SharedPreference(this).save("automatic", gameController!!.automatic)
        SharedPreference(this).save("update2", gameController!!.priceList[1])
        SharedPreference(this).save("update3", gameController!!.priceList[2])
        SharedPreference(this).save("update4", gameController!!.priceList[3])
        SharedPreference(this).save("update5", gameController!!.priceList[4])
    }
}