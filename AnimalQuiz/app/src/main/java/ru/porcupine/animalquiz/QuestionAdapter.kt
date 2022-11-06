package ru.porcupine.animalquiz
import android.app.Application
import android.util.Log

class QuestionAdapter() {

    fun readFromAsset(application: Application): MutableList<QuestionModel> {
        var questList:MutableList<QuestionModel> = arrayListOf()
        val file_name = "data.txt"
        var i=0
        val bufferReader =  application.assets.open(file_name).bufferedReader()
        bufferReader.forEachLine{
            var arrChar = it.split(" / ")
//            Log.d("Read", it)
//            Log.d("Read", "${arrChar[0]}, ${arrChar[1]}, ${arrChar[2]}, ${arrChar[3]}, ${arrChar[4]}")
            questList.add(QuestionModel(arrChar[0], arrChar[1], arrChar[2], arrChar[3], arrChar[4]))
            i++
        }
        questList.forEach() {Log.d("Read", it.trueAnswer)}

        return questList;
    }
}