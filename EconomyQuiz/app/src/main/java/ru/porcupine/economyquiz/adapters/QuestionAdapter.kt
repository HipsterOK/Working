package ru.porcupine.economyquiz.adapters
import android.app.Application
import ru.porcupine.economyquiz.models.QuestionModel

class QuestionAdapter {

    fun readFromAsset(application: Application): MutableList<QuestionModel> {
        val questList:MutableList<QuestionModel> = arrayListOf()
        val fileName = "data.txt"
        var i=0
        val bufferReader =  application.assets.open(fileName).bufferedReader()
        bufferReader.forEachLine{
            val arrChar = it.split(" / ")
            questList.add(QuestionModel(arrChar[0], arrChar[1], arrChar[2], arrChar[3], arrChar[4]))
            i++
        }

        return questList
    }
}