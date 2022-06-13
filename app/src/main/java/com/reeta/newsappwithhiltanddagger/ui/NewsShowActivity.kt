package com.reeta.newsappwithhiltanddagger.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.reeta.newsappwithhiltanddagger.R

class NewsShowActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var titleView: TextView
    lateinit var timeView: TextView
    lateinit var desView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_show)
        imageView=findViewById(R.id.imageview)
        titleView=findViewById(R.id.newsTitle)
        timeView=findViewById(R.id.newsTime)
        desView=findViewById(R.id.newsDescription)
        val intent=intent
        var image: String? =intent.getStringExtra("image")
        var title=intent.getStringExtra("title")
        var time=intent.getStringExtra("time")
        var description=intent.getStringExtra("description")
        Glide.with(imageView).load(image).into(imageView)
        titleView.text=title
        timeView.text=time
        desView.text=description
    }
}