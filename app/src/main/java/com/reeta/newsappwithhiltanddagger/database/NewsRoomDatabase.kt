package com.reeta.newsappwithhiltanddagger.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [NewsDbTable::class], version = 1)
abstract class NewsRoomDatabase(): RoomDatabase() {

    abstract fun getDao(): NewsDao

    companion object {
        private var INSTANCE: NewsRoomDatabase? = null

        fun getDatabaseObject(context: Context): NewsRoomDatabase {
                /*
            //Migration
            val MIGRATIION_1_2:Migration?= object :Migration(1,2){
                override fun migrate(database: SupportSQLiteDatabase) {

                    //create new table
                    database.execSQL("CREATE TABLE user_new (userid TEXT, userName TEXT, PRIMARY KEY last_update INTEGER )")

                    //copy the data
                    database.execSQL("INSERT INTO user_new(userid,userName,last_name) SELECT userid,userName,last_name")

                    //Remove the old table
                    database.execSQL("DROP TABLE users")

                    //change the table name to correct one
                    database.execSQL("ALTER TABLE user_new RENAME To users")


                }

            }*/

            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, NewsRoomDatabase::class.java, "news_database")
                    .build()
                INSTANCE!!
            }else INSTANCE!!

        }
    }
}