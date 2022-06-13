package com.reeta.newsappwithhiltanddagger.di

import android.content.Context
import androidx.room.Room
import com.reeta.newsappwithhiltanddagger.database.NewsDao
import com.reeta.newsappwithhiltanddagger.database.NewsRoomDatabase
import com.reeta.newsappwithhiltanddagger.response.ApiCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun provideAPI():ApiCall{
        val builder=Retrofit.Builder().baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        return builder.create(ApiCall::class.java)
    }

    fun provideRoomDb(@ApplicationContext context: Context):NewsRoomDatabase{
      val builder=  Room.databaseBuilder(context,NewsRoomDatabase::class.java,"news_database")
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    fun provideNewsDao(db:NewsRoomDatabase):NewsDao{
        return db.getDao()
    }


}