package ru.porcupine.runcatrun.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.porcupine.runcatrun.SharedPreference

class GameState(
    context:Context,
    initialScore: Int = 0,
    initialHighScore: Int = SharedPreference(context).getValueInt("record"),
    var isGameOver: Boolean = false
) {

    private val _currentScore: MutableLiveData<Int> = MutableLiveData(initialScore)
    val currentScore: LiveData<Int>
        get() = _currentScore

    val _highScore: MutableLiveData<Int> = MutableLiveData(initialHighScore)
    val highScore: LiveData<Int>
        get() = _highScore

    fun increaseScore() {
        requireNotNull(_currentScore.value).inc()
        _currentScore.value = requireNotNull(_currentScore.value).inc()
    }

    fun replay(context: Context) {
        val score = requireNotNull(_currentScore.value)
        val high = requireNotNull(_highScore.value)
        _highScore.value = if (score > high) score else high
        SharedPreference(context).save("record", _highScore.value!!)
        _currentScore.value = 0
        isGameOver = false
    }
}