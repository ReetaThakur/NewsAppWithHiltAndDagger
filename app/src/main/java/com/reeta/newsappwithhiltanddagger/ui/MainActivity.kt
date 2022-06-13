package com.reeta.newsappwithhiltanddagger.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reeta.newsappwithhiltanddagger.R
import com.reeta.newsappwithhiltanddagger.database.NewsDao
import com.reeta.newsappwithhiltanddagger.database.NewsDbTable
import com.reeta.newsappwithhiltanddagger.database.NewsRoomDatabase
import com.reeta.newsappwithhiltanddagger.factory.Factory
import com.reeta.newsappwithhiltanddagger.repository.Repository
import com.reeta.newsappwithhiltanddagger.response.Article
import com.reeta.newsappwithhiltanddagger.response.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NewsListner {

    private lateinit var newsAdapter: NewsAdapter
    val list = mutableListOf<Article>()
    val newsList =ArrayList<NewsDbTable>()

    //room related objects

    private lateinit var newsDao: NewsDao
    private lateinit var repo: Repository
    private lateinit var roomDb: NewsRoomDatabase

    val viewModel:NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        roomDb = NewsRoomDatabase.getDatabaseObject(this)
//        newsDao = roomDb.getDao()
//        repo = Repository(newsDao)
//        val factory = Factory(repo)
//
//
//        viewModel = ViewModelProviders.of(this, factory).get(NewsViewModel::class.java)

        viewModel.getNews()
        viewModel.getDataFromApi().observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                  Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    list.clear()
                    it.data?.articles.let {
                        if (it != null) {
                            list.addAll(it)
                            newsAdapter.notifyDataSetChanged()
                        }
                    }
                }
                Status.LOADING ->{
                    Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
                }
            }
        })
        setRecycelerView()
    }

    private fun setRecycelerView() {
        newsAdapter = NewsAdapter(list, this)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = newsAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun newsClick(article: Article) {
        var image = article.urlToImage
        var title = article.title
        var time = article.publishedAt
        var description = article.description
        Log.d("activity", image)
        val intent = Intent(this, NewsShowActivity::class.java)
        intent.putExtra("image", image)
        intent.putExtra("title", title)
        intent.putExtra("time", time)
        intent.putExtra("description", description)
        startActivity(intent)
    }
}