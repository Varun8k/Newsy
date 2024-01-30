package com.example.Newsy

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val Base_url="https://newsapi.org/"
const val Api_key="985f7c4605b34a63bebda80de22554ec"
interface Newsinterface {
    @GET("v2/top-headlines?apiKey=$Api_key")
    fun getheadlines(@Query("country") country: String, @Query("page")page: Int,@Query("category") category: String=""):Call<News>
}


object RetrofitInstance{
    val instance:Newsinterface
    init {
 val retrofit=Retrofit.Builder()
     .baseUrl(Base_url)
     .addConverterFactory(GsonConverterFactory.create())
     .build()
        instance=retrofit.create(Newsinterface::class.java)
    }
}

