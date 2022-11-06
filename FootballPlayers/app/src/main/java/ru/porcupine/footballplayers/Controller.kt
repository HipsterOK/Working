package ru.porcupine.footballplayers

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Controller(context: Context) {
    val connect = Connect()
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/80

    var openIdCard by mutableStateOf(0)
    var cardPos by mutableStateOf(screenWidth)
    var lazyPos by mutableStateOf(0f)

    var position: String? by mutableStateOf(null)
    var goal: String? by mutableStateOf(null)
    var assists: String? by mutableStateOf(null)
    var teamPhoto: String? by mutableStateOf(null)
    var teamName: String? by mutableStateOf(null)
    var firstName: String? by mutableStateOf(null)
    var lastName: String? by mutableStateOf(null)
    var playerPhoto: String? by mutableStateOf(null)
    var playerWeight: String? by mutableStateOf(null)
    var playerHeight: String? by mutableStateOf(null)
    var playerAge: String? by mutableStateOf(null)

    fun getPlayers(league:Long, season:Int){
        connect.getPlayers("https://api-football-v1.p.rapidapi.com/v3/players?league=${league}&season=${season}")
        CoroutineScope(Dispatchers.IO).launch {
            while (connect.listPlayers.isEmpty()){
                if(connect.listPlayers.isNotEmpty()) {
                    fillDataCard()
                }
//                delay(1000)
            }
        }
    }
    fun fillDataCard() {
        CoroutineScope(Dispatchers.IO).launch {
            connect.loading = 1f
            position = connect.listPlayers[openIdCard].statistics!![0].games!!.position.toString()
            goal =
                if (connect.listPlayers[openIdCard].statistics!![0].goals!!.total.toString() == "null") {
                    "\nNo information\navailable"
                } else connect.listPlayers[openIdCard].statistics!![0].goals!!.total.toString()
            assists =
                if (connect.listPlayers[openIdCard].statistics!![0].goals!!.assists.toString() == "null") {
                    "\nNo information\navailable"
                } else connect.listPlayers[openIdCard].statistics!![0].goals!!.assists.toString()
            teamPhoto = connect.listPlayers[openIdCard].statistics!![0].team!!.logo.toString()
            teamName = connect.listPlayers[openIdCard].statistics!![0].team!!.name.toString()
            firstName = connect.listPlayers[openIdCard].player!!.firstname.toString()
            lastName = connect.listPlayers[openIdCard].player!!.lastname.toString()
            playerPhoto = connect.listPlayers[openIdCard].player!!.photo.toString()
            playerWeight =
                if (connect.listPlayers[openIdCard].player!!.weight.toString() == "null") {
                    "No information\navailable"
                } else connect.listPlayers[openIdCard].player!!.weight.toString()
            playerHeight =
                if (connect.listPlayers[openIdCard].player!!.height.toString() == "null") {
                    "No information\navailable"
                } else connect.listPlayers[openIdCard].player!!.height.toString()
            playerAge = if (connect.listPlayers[openIdCard].player!!.age.toString() == "null") {
                "No information\navailable"
            } else connect.listPlayers[openIdCard].player!!.age.toString()
            connect.loading = 0f
        }
    }
}