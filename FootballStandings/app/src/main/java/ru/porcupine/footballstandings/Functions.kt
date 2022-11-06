package ru.porcupine.footballstandings

import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class Functions {
    fun getDate(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-dd-mm")
        return formatter.format(time)
    }

    fun score(mainViewModel: MainViewModel){
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.scoreText = mutableListOf()
            mainViewModel.fixturesListResponse.forEach {
                if (it.score!!.fulltime!!.home != null) {
                    mainViewModel.scoreText.add("${it.score.fulltime!!.home.toString()}:${it.score.fulltime.away.toString()}")
                } else mainViewModel.scoreText.add("Not started")
            }
        }
    }

    fun loadTeamFilterList(mainViewModel:MainViewModel){
        CoroutineScope(Dispatchers.IO).launch {
        val mList1 = mutableListOf("All")
        mainViewModel.fixturesListResponseFirst.forEach{ it1 ->
            mList1.add(it1.teams!!.home!!.name.toString())
            mList1.add(it1.teams.away!!.name.toString())
        }
        val set: Set<String> = LinkedHashSet(mList1)
            mainViewModel.filterTeamList = (set.toMutableStateList())
        }
    }

    fun loadLeagueFilterList(mainViewModel:MainViewModel){
        CoroutineScope(Dispatchers.IO).launch {
            val mList1 = mutableListOf("All")
            mainViewModel.fixturesListResponseFirst.forEach{ it1 ->
                mList1.add(it1.league!!.name.toString())
            }
            val set: Set<String> = LinkedHashSet(mList1)
            mainViewModel.filterLeagueList = (set.toMutableStateList())
        }
    }
}