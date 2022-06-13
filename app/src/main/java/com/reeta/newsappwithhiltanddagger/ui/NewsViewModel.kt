package com.reeta.newsappwithhiltanddagger.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.reeta.newsappwithhiltanddagger.database.NewsDbTable
import com.reeta.newsappwithhiltanddagger.repository.Repository
import com.reeta.newsappwithhiltanddagger.response.Resource
import com.reeta.newsappwithhiltanddagger.response.ResponseDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val repository: Repository): ViewModel() {

    fun getDataFromApi(): LiveData<Resource<ResponseDTO>> {
        return liveData(Dispatchers.IO) {
            val result=  repository.getApiResponse()
            emit(result)
        }
    }

    fun getNews() {
        repository.getAllNewsFromDb()
    }


/*
    fun getNewsFromApi(){
       return repository.getDataFromApi()
    }



    fun insertDataInDb(newsDbTable: NewsDbTable){
        repository.addNewsToRoom(newsDbTable)
    }



    fun updateNews(newsDbTable: NewsDbTable){
        return repository.updateAll(newsDbTable)
    }

    fun deleteNews(){
        repository.delete()
    }

    */


}