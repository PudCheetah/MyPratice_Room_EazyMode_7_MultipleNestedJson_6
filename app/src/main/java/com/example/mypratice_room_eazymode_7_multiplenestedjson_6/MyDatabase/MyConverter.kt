package com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyConverter {
    class ConverterForField{
        @TypeConverter
        fun listFieldToString(list: List<Field>): String{
            return Gson().toJson(list)
        }
        @TypeConverter
        fun stringToListField(string: String): List<Field>{
            var listType = object: TypeToken<List<Field>>(){}.type
            return Gson().fromJson(string, listType)
        }
    }
    class ConverterForTsunami{
        @TypeConverter
        fun listTsunamiToString(list: List<Tsunami>): String{
            return Gson().toJson(list)
        }
        @TypeConverter
        fun stringToListTsunami(string: String): List<Tsunami>{
            var listType = object: TypeToken<List<Tsunami>>(){}.type
            return Gson().fromJson(string, listType)
        }
    }
    class ConverterForWarningArea{
        @TypeConverter
        fun listWarningAreaToString(list: List<WarningArea>): String{
            return Gson().toJson(list)
        }
        @TypeConverter
        fun stringToListWarningArea(string: String): List<WarningArea>{
            var listType = object: TypeToken<List<WarningArea>>(){}.type
            return Gson().fromJson(string, listType)
        }
    }
    class ConverterForTsuStation{
        @TypeConverter
        fun listTsuStationToString(list: List<TsuStation>): String{
            return Gson().toJson(list)
        }
        @TypeConverter
        fun stringToListTsuStation(string: String): List<TsuStation>{
            var listType = object: TypeToken<List<TsuStation>>(){}.type
            return Gson().fromJson(string, listType)
        }
    }
}