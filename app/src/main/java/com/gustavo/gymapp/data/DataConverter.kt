package com.gustavo.gymapp.data

import androidx.room.TypeConverter
import java.util.Date

object DataConverter{

    @TypeConverter
    fun toDate(dataLong:Long?):Date?{
        return if(dataLong!=null)Date(dataLong) else null
    }

    @TypeConverter
    fun fromDate(date:Date?):Long?{
        return date?.time
    }


}