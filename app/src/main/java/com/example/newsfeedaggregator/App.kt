package com.example.newsfeedaggregator

import android.app.Application
import androidx.room.Room
import com.example.newsfeedaggregator.data.database.NewsDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        db = Room.databaseBuilder(
            applicationContext,
            NewsDatabase::class.java, "news-database"
        ).build()
    }

    companion object {
        lateinit var application: Application
        val context get() = application.applicationContext
        lateinit var db: NewsDatabase
    }
}