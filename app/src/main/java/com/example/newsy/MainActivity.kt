package com.example.newsy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Newsy.Articles
import com.example.Newsy.News
import com.example.Newsy.NewsAdapter
import com.example.Newsy.RetrofitInstance
import com.example.newsy.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles= mutableListOf<Articles>()
    var isLoading=false
    var totalResult=-1
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, articles)
        val newsList = findViewById<RecyclerView>(R.id.newsList)
        newsList.adapter = adapter
        getnews()
        newsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                Log.d("scroll", "$lastVisibleItemPosition")
                Log.d("scroll", "$totalItemCount")
                if (!isLoading&&totalResult>totalItemCount &&  lastVisibleItemPosition >= totalItemCount-3) {
                    page++
                    getnews()
                }

            }
        })
    }

    private fun getnews() {
        Log.d("scroll", "Request send for page number $page")
        isLoading=true
        val news = RetrofitInstance.instance.getheadlines("in", page)
        news.enqueue(object : Callback<News> {

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    totalResult=news.totalResults
                   articles.addAll(news.articles)
                   adapter.notifyDataSetChanged()
                    isLoading=false
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("failded", "nothing")
            }
        })
    }
}

