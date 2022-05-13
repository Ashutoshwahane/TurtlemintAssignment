package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem

@Database(
    entities = [ApiResponseItem::class],
    version = 1
)

abstract class MainDatabase : RoomDatabase() {

    abstract fun getissues(): IssueDao

    companion object {
        @Volatile
        private var instance: MainDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
            context.applicationContext,
                MainDatabase::class.java,
        "issue.db"
        ).build()


    }

}