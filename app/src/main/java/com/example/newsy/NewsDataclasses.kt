package com.example.Newsy


data class News(
    val totalResults:Int,
    val articles:List<Articles>
)
data class Articles(
val title:String,val description:String,val author:String,val url:String,val urlToImage:String,
)
