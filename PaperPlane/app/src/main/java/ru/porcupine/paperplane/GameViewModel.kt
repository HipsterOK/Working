package ru.porcupine.paperplane

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.max

class GameViewModel(size: ScreenSize, context: Context) : ViewModel() {

    private var _state = MutableLiveData<GameUI>(GameUI.NotStarted)
    val state: LiveData<GameUI> = _state

    private var _scoreBoard = MutableLiveData(Scoreboard(0, SharedPreference(context).getValueInt("record")))
    val scoreBoard: LiveData<Scoreboard> = _scoreBoard

    private var hasStarted = false
    private var bestScore = SharedPreference(context).getValueInt("record")
    private var game = Game(size)

    fun onGameBoundsSet(widthDp: Float, heightDp: Float) {
        game.setBounds(widthDp, heightDp)
    }

    fun onTap() {
        if(hasStarted) {
            game.jump()
        } else {
            startGame()
        }
    }

    private fun startGame() {
        viewModelScope.launch(Dispatchers.IO) {
            hasStarted = true
            game.start().collectLatest {
                _state.postValue(it)
                when(it) {
                    is GameUI.Playing -> updateScore(it)
                    is GameUI.Finished -> hasStarted = false
                }
            }
        }
    }

    private fun updateScore(current: GameUI.Playing) {
        bestScore = max(bestScore, current.score)

        _scoreBoard.postValue(
            Scoreboard(
                current = current.score,
                best = bestScore
            )
        )
    }

}
