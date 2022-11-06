package ru.porcupine.catchafish

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FishViewModel(context: Context) {

    var fishList = mutableListOf<FishModel>()
    var score: Int by mutableStateOf(0)
    private val viewModel = MainActivityView(context)
    val fishWidth = viewModel.dpWidth/15
    val fishHight = fishWidth/2
    var fishFall = mutableStateListOf(
        (viewModel.dpHeight.toInt()..(viewModel.dpHeight*3).toInt()).random(),
        (viewModel.dpHeight.toInt()..(viewModel.dpHeight*3).toInt()).random(),
        (viewModel.dpHeight.toInt()..(viewModel.dpHeight*3).toInt()).random(),
        (viewModel.dpHeight.toInt()..(viewModel.dpHeight*3).toInt()).random(),
        (viewModel.dpHeight.toInt()..(viewModel.dpHeight*3).toInt()).random()
    )
    private set

    var paddingList = mutableStateListOf(
        0,0,0,0,0
    )
    private set
    var enable = mutableListOf(true, true, true, true, true)


    private fun createFish(){
        fishList.add(FishModel(R.drawable.fish, true))
        fishList.add(FishModel(R.drawable.fish, true))
        fishList.add(FishModel(R.drawable.fish, true))
        fishList.add(FishModel(R.drawable.sceleton, false))
        fishList.add(FishModel(R.drawable.sceleton, false))
    }

    private fun setPadding(){
        for(i in 0..4){
            paddingList[i]= (0..(viewModel.dpWidth-fishWidth).toInt()).random()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun spawn(viewModel: MainActivityView){
        setPadding()
        createFish()
        GlobalScope.launch {
            while (viewModel.time>0 && viewModel.HP>0) {
                Log.i("XCat", viewModel.catX.toString())
                whiling(0, viewModel)
                whiling(1, viewModel)
                whiling(2, viewModel)
                whiling(3, viewModel)
                whiling(4, viewModel)
                delay(5)
            }
        }

    }

    fun whiling(index: Int, viewModel: MainActivityView){
        if(fishFall[index]>fishHight && enable[index]){
            fishFall[index]--

            if(viewModel.catX<=paddingList[index] &&
                paddingList[index]+fishWidth<= viewModel.catWidth+viewModel.catX &&
                fishList[index].fish &&
                0<=fishFall[index] &&
                fishFall[index]<=viewModel.catHeight) {
                score++
                enable[index]=false
            }
            if(viewModel.catX<=paddingList[index] &&
                paddingList[index]+fishWidth<= viewModel.catWidth+viewModel.catX &&
                !fishList[index].fish &&
                0<=fishFall[index] &&
                fishFall[index]<=viewModel.catHeight) {
                viewModel.HP--
                viewModel.HPState[viewModel.HP]=0f
                enable[index]=false
            }

        } else{
            enable[index]=false
            if(!enable[index]) {
                fishFall[index] =
                    (viewModel.dpHeight.toInt()..(viewModel.dpHeight * 3).toInt()).random()
                paddingList[index] = (0..(viewModel.dpWidth - fishWidth).toInt()).random()
                enable[index]=true
            }
        }
    }
}