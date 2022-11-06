package ru.porcupine.footballstandings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.footballstandings.`interface`.ApiService
import ru.porcupine.footballstandings.fixtures.Response

class MainViewModel : ViewModel() {
    var fixturesListResponse:List<Response> by mutableStateOf(listOf())
    var fixturesListResponseFirst:List<Response> = listOf()
    var scoreText:MutableList<String> by mutableStateOf(mutableListOf())
    private var errorMessage: String by mutableStateOf("")
    var findLeague:String = ""
    var findTeam:String = ""
    var filterLeague: Boolean = false
    var filterTeam: Boolean = false

    var filterTeamList = mutableStateListOf<String>()
    var filterLeagueList = mutableStateListOf<String>()


    fun getFixturesToday() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = ApiService.getInstance()
            while (true) {
                    try {
                        val fixtures = apiService.getFixturesToday()
                        fixturesListResponse = fixtures.execute().body()!!.response!!
                        fixturesListResponseFirst = fixturesListResponse
                        Functions().score(this@MainViewModel)
                        Functions().loadTeamFilterList(this@MainViewModel)
                        Functions().loadLeagueFilterList(this@MainViewModel)
                    } catch (e: Exception) {
                        errorMessage = e.stackTraceToString()
                        Log.i("ErrorFixtures", errorMessage)
                    }
                if(filterLeague){
                    Filters().filterLeague(findLeague, this@MainViewModel)
                }
                if(filterTeam){
                    Filters().filterTeam(findTeam, this@MainViewModel)
                }
                delay(180000)
            }
        }
    }
}
