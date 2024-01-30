package com.example.Newsy

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsy.R



class NewsAdapter(private val context:Context, private val articles: List<Articles>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.newsTitle)
        val desc: TextView = view.findViewById(R.id.newsDescription)
        val newsImage: ImageView = view.findViewById(R.id.newsImage)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.news_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val article=articles[position]
        viewHolder.title.text = article.title
        viewHolder.desc.text = article.description
        Glide.with(context).load(article.urlToImage).into(viewHolder.newsImage)
        viewHolder.itemView.setOnClickListener{
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(context, Uri.parse(article.url))

        }

    }

    override fun getItemCount() = articles.size

}


