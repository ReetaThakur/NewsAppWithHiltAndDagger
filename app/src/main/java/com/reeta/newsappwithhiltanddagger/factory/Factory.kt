package com.reeta.newsappwithhiltanddagger.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reeta.newsappwithhiltanddagger.repository.Repository
import com.reeta.newsappwithhiltanddagger.ui.NewsViewModel

class Factory(private val repo: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repo) as T
    }
}