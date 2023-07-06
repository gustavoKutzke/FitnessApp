package com.gustavo.gymapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Calc::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun calcDao():CalcDao

    companion object{

        private var INSTANCE:AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "GymApp"
                    ).build()
                }
                return INSTANCE as AppDatabase
            }else{
                return INSTANCE as AppDatabase
            }
        }
    }

}