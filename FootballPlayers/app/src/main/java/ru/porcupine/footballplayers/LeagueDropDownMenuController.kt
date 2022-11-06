package ru.porcupine.footballplayers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size

class LeagueDropDownMenuController(leagueConnect: LeagueConnect) {
    var expanded by mutableStateOf(false)
    var items by mutableStateOf(leagueConnect.listText)
    var selectedIndex by mutableStateOf(0)
    var textFieldSize by mutableStateOf(Size.Zero)
}