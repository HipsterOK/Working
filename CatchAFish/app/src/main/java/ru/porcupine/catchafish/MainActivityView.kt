package ru.porcupine.catchafish

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


class MainActivityView(context: Context) : ViewModel() {

    private var displayMetrics = context.resources.displayMetrics
    var dpHeight = displayMetrics.heightPixels / displayMetrics.density
    var dpWidth = displayMetrics.widthPixels / displayMetrics.density
    var catWidth = dpWidth/9
    val catHeight = catWidth
    var time: Int by mutableStateOf(30)
    var catX: Int by mutableStateOf(((dpWidth-catWidth)/2).toInt())
    var HPState = mutableStateListOf(1f, 1f, 1f)
    var HP=3
    var painterId:Int by mutableStateOf(R.drawable.cat_right)


    @OptIn(DelicateCoroutinesApi::class)
    fun startTimer(context: Context, fishViewModel: FishViewModel){
        GlobalScope.launch(Dispatchers.Main) {
            while(time>0 && HP>0){
                delay(1000)
                time--
            }
            val resultActivity = Intent(context, FinalActivity::class.java)
            resultActivity.putExtra("score", fishViewModel.score)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultActivity)
            MainActivity().finishActiv()

        }
    }
}
