package com.reeta.newsappwithhiltanddagger.ui

import com.reeta.newsappwithhiltanddagger.response.Article

interface NewsListner {

    fun newsClick(article: Article)
}