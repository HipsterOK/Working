package ru.porcupine.footballplayers

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.porcupine.footballplayers.models.league.LeaguesModel
import ru.porcupine.footballplayers.models.league.Response
import java.net.URL

class LeagueConnect {
    var loading by mutableStateOf(1f)
    var listLeagues:List<Response> by mutableStateOf(listOf())
    var listText:MutableList<String> by mutableStateOf(mutableListOf(""))

    fun getLeagues(sUrl: String) {
        val client = OkHttpClient()
        CoroutineScope(Dispatchers.IO).launch {
            loading = 1f
            var result: String? = null
            try {
                val url = URL(sUrl)
                val request = Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("X-RapidAPI-Key", "38d6a3f81cmsh9b03bc03de94120p1ac27cjsn0b61aacd9236")
                    .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string()
            }
            catch(err:Exception) {
                Log.i("error","Error when executing get request: "+err.stackTraceToString())
            }
            Log.i("result", result.toString())

            if (result != null) {
                try {
                    listLeagues = LeaguesModel.fromJson(result).response!!
                } catch (err: Exception) {
                    Log.i("error1", "Error when parsing JSON: " + err.stackTraceToString())
                }
            } else {
                Log.i("error2", "Error: Get request returned no response")
            }
            listText.clear()
            for (element in listLeagues){
                listText.add(element.league?.name.toString())
            }

            val mList1:MutableList<String> = mutableListOf()
            listLeagues.forEach{ it1 ->
                mList1.add(it1.league!!.name.toString())
            }
            val set: Set<String> = LinkedHashSet(mList1)
            listText = (set.toMutableList())

            loading = 0f
        }
    }
}