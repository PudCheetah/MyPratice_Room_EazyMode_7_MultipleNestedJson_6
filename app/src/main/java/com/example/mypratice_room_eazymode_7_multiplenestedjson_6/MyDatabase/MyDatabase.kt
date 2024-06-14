package com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL


@Database(entities = arrayOf(Records::class), version = 4)
abstract class MyDatabase() : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        private var instance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context? = null): MyDatabase? {
            if(instance != null){
                return instance
            }else{
                instance = Room.databaseBuilder(context!!, MyDatabase::class.java, "MyDatabase").fallbackToDestructiveMigration().build()
                return instance
            }
        }
        suspend fun insertInternetData(): Job {
            var myInternetDataInsertJob = CoroutineScope(Dispatchers.IO).launch {
                val myUrl =
                    URL("https://opendata.cwa.gov.tw/api/v1/rest/datastore/E-A0014-001?Authorization=CWA-1394A705-AF6D-4DD6-9D2A-28ABBA9CF3B6&format=JSON").readText()
                getInstance()?.myDao()?.insertData(Gson().fromJson(myUrl, MyEntity::class.java))
            }
            return myInternetDataInsertJob
        }
    }
}