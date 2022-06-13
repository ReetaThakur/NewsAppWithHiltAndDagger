package com.reeta.newsappwithhiltanddagger.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListOfNews(newsDbTable: ArrayList<NewsDbTable>)


    @Query("select * from news_table")
    fun getResponseFromDb(): LiveData<List<NewsDbTable>>

    @Query("delete from news_table")
    fun deleteAllDataFromDb()




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDataFromDb(newsDbTable: NewsDbTable)

    @Update
    fun update(newsDbTable: NewsDbTable)

    @Delete
    fun delete(newsDbTable: NewsDbTable)

}