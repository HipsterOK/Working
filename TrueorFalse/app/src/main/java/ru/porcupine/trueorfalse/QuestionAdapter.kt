package ru.porcupine.trueorfalse
import android.app.Application
import android.util.Log

class QuestionAdapter() {

    fun readFromAsset(application: Application): MutableList<QuestionModel> {
        val questList:MutableList<QuestionModel> = arrayListOf()
        val fileName = "data.txt"
        val bufferReader =  application.assets.open(fileName).bufferedReader()
        bufferReader.forEachLine{
            var arrChar = it.split(" / ")
            questList.add(QuestionModel(arrChar[0], arrChar[1].toBoolean(), arrChar[2]))
        }
        questList.forEach() {Log.d("Read", it.trueAnswer.toString())}

        return questList;
    }
}