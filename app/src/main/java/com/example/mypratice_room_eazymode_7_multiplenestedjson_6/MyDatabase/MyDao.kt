package com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(myEntity: MyEntity)

    @Query("select * from Records ORDER BY primaryKey")
    fun getAll(): List<MyEntity>

    @Query("delete from Records where primaryKey > 0")
    suspend fun deleteAll()

    @Query("select count(*) from Records")
    suspend fun getCount(): Int

    @Query("select * from Records ORDER BY primaryKey LIMIT 1 OFFSET :index")
    suspend fun geByIndext(index: Int): MyEntity?
}