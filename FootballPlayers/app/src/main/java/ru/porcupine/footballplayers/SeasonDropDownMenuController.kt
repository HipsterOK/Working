package ru.porcupine.footballplayers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SeasonDropDownMenuController {
    val items = listOf("2008", "2009", "2010", "2011", "2012", "2013",
        "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022")
    var selectedIndex by mutableStateOf(items.lastIndex)
}