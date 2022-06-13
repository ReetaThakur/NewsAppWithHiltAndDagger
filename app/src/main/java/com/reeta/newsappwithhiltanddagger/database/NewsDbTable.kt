package com.reeta.newsappwithhiltanddagger.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsDbTable(@ColumnInfo(name = "title")val title:String,
@ColumnInfo(name = "newsImage") val newsImage:String){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int?=null
}