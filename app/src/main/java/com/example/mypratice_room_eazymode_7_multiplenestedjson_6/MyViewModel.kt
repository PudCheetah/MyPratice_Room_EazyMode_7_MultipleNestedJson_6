package com.example.mypratice_room_eazymode_7_multiplenestedjson_6

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Index
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase.MyDao
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase.MyDatabase
import com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase.MyEntity

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MyViewModel(application: Application) : AndroidViewModel(application) {
    var myDataList = MutableLiveData<List<MyEntity>>()

    private var myDao: MyDao? = null
    private lateinit var myViewModelInitJob: Job

    init {
        myViewModelInitJob = viewModelScope.launch(Dispatchers.IO) {
                myDao = MyDatabase.getInstance(application)?.myDao()
                myDataList.postValue(myDao?.getAll())
        }
    }

    fun getMyVMInitJob(): Job {
        return myViewModelInitJob
    }

    fun updateMyDataList(): Job {
        var VM_updateMyDataListJob = viewModelScope.launch(Dispatchers.IO) {
            myDataList.postValue(myDao?.getAll())
        }
        return VM_updateMyDataListJob
    }
}