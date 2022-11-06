package ru.porcupine.fruitseeker

class TimeSeparator {
    fun timeToString(time:Int):String{
        val timeStr: String
        val min = time/60
        val sec = time%60
        timeStr="${min.toString().padStart(2,'0')}:${ sec.toString().padStart(2,'0')}"
        return timeStr
    }
}