package ru.porcupine.footballstandings.`interface`

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.porcupine.footballstandings.Functions
import ru.porcupine.footballstandings.fixtures.FixturesModel


interface ApiService {
    @Headers("X-RapidAPI-Key: 38d6a3f81cmsh9b03bc03de94120p1ac27cjsn0b61aacd9236",
        "X-RapidAPI-Host: api-football-v1.p.rapidapi.com")
    @GET("fixtures")
    fun getFixturesToday(
        @Query("date") date:String = Functions().getDate()
    ): Call<FixturesModel>

    companion object {
        private var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api-football-v1.p.rapidapi.com/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}