package com.reeta.newsappwithhiltanddagger.repository

import androidx.lifecycle.LiveData
import com.reeta.newsappwithhiltanddagger.database.NewsDao
import com.reeta.newsappwithhiltanddagger.database.NewsDbTable
import com.reeta.newsappwithhiltanddagger.response.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val newsDao: NewsDao) {

    private val apiCall= Network.getRetrofit().create(ApiCall::class.java)

    private val responseHandler= ResponseHandler()

    suspend fun getApiResponse(): Resource<ResponseDTO> {
        return try {
            val responseDTO= apiCall.getInstance(Network.country,Network.apiKey)
            saveToDb(responseDTO)
            responseHandler.handleSuccess(responseDTO)
        }catch (e: Exception){
            responseHandler.handleException(e)
        }
    }

    private fun saveToDb(responseDTO: ResponseDTO) {
        val listOfNews =ArrayList<NewsDbTable>()
        responseDTO.articles.forEach {
            var news=NewsDbTable(it.title,it.urlToImage)
            listOfNews.add(news)
        }
        newsDao.deleteAllDataFromDb()
        newsDao.addListOfNews(listOfNews)
    }

    fun getAllNewsFromDb(): LiveData<List<NewsDbTable>> {
        return newsDao.getResponseFromDb()
    }



/*
      fun addNewsToRoom(newsDbTable: NewsDbTable){
          CoroutineScope(Dispatchers.IO).launch {
              newsDao.deleteAllDataFromDb()
              newsDao.addDataFromDb(newsDbTable)
          }
      }

       fun delete(){
          newsDao.deleteAllDataFromDb()
      }


      fun  updateAll(newsDbTable: NewsDbTable){
          CoroutineScope(Dispatchers.IO).launch {
              newsDao.update(newsDbTable)
          }
      }

 */

}