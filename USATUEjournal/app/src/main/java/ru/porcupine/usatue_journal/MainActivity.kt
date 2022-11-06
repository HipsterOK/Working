package ru.porcupine.usatue_journal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.http.promisesBody
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v3/standings?season=2020&league=39")
                .get()
                .addHeader("X-RapidAPI-Key", "38d6a3f81cmsh9b03bc03de94120p1ac27cjsn0b61aacd9236")
                .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                .build()

            val response = client.newCall(request).execute()
            val jsonDataString = response.body?.string()

            val json = JSONObject(jsonDataString)
            if (!response.isSuccessful) {
                val errors = json.getJSONArray("errors").join(", ")
                throw Exception(errors)
            }
            Log.i("ss", json.toString())
        }


        setContent {

        }
    }
}