package ru.porcupine.footballstandings

import ru.porcupine.footballstandings.fixtures.Response

class Filters {
    fun filterLeague(find:String, mainViewModel: MainViewModel){
        var findList=mainViewModel.fixturesListResponseFirst
        if(find == "All"){
            if(mainViewModel.filterTeam) {
                filterTeam(mainViewModel.findTeam, mainViewModel)
            } else mainViewModel.fixturesListResponse = mainViewModel.fixturesListResponseFirst
        } else {
            if(mainViewModel.filterTeam){
                findList = mainViewModel.fixturesListResponse
            }
            mainViewModel.filterLeague=true
            val newList = mutableListOf<Response>()
           findList.forEach {
                if (it.league!!.name == find) {
                    newList.add(it)
                }
            }
            mainViewModel.fixturesListResponse = newList
        }
    }

    fun filterTeam(find:String, mainViewModel: MainViewModel){
        var findList=mainViewModel.fixturesListResponseFirst
        if(find == "All"){
            mainViewModel.filterTeam=false
            if(mainViewModel.filterLeague) {
                filterLeague(mainViewModel.findLeague, mainViewModel)
            } else mainViewModel.fixturesListResponse = mainViewModel.fixturesListResponseFirst
        } else {
            if(mainViewModel.filterLeague){
               findList = mainViewModel.fixturesListResponse
            }
            mainViewModel.filterTeam=true
            val newList = mutableListOf<Response>()
            findList.forEach {
                if (it.teams!!.home!!.name == find) {
                    newList.add(it)
                }
                if (it.teams.away!!.name == find) {
                    newList.add(it)
                }
            }
            mainViewModel.fixturesListResponse = newList
        }
    }
}