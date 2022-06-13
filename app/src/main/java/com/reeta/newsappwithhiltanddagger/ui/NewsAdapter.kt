package com.reeta.newsappwithhiltanddagger.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reeta.newsappwithhiltanddagger.R
import com.reeta.newsappwithhiltanddagger.response.Article

class NewsAdapter(val list: List<Article>, val listner: NewsListner): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_layout,parent,false)
        return NewsViewHolder(view,listner)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsList =list[position]
        holder.setData(newsList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NewsViewHolder(itemView: View, val listner: NewsListner): RecyclerView.ViewHolder(itemView){
        val newsImage: ImageView =itemView.findViewById(R.id.newsImage)
        val newsHeadings: TextView =itemView.findViewById(R.id.tvNewsHeading)
        val cardView: CardView =itemView.findViewById(R.id.cardView)
        fun setData(article:Article){
            newsHeadings.text=article.title
            Glide.with(newsImage).load(article.urlToImage).into(newsImage)

            cardView.setOnClickListener {
                listner.newsClick(article)
            }
        }
    }

}