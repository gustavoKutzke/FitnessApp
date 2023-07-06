package com.gustavo.gymapp

import android.app.Application
import com.gustavo.gymapp.data.AppDatabase

class App:Application() {

    lateinit var db:AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = AppDatabase.getDatabase(this)
    }


}