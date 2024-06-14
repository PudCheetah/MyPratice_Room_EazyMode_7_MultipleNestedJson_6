package com.example.mypratice_room_eazymode_7_multiplenestedjson_6.MyDatabase
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


data class MyEntity(
    @Ignore
    @SerializedName("success")
    val success: String,
    @Ignore
    @SerializedName("result")
    val result: Result,
    @SerializedName("records")
    val records: Records
)
@TypeConverters(MyConverter.ConverterForField::class)
data class Result(
    @SerializedName("resource_id")
    val resourceId: String,
    @SerializedName("fields")
    val fields: List<Field>
)
@Entity
@TypeConverters(MyConverter.ConverterForTsunami::class)
data class Records(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Long,
    @SerializedName("datasetDescription")
    val datasetDescription: String,
    @SerializedName("Tsunami")
    val tsunami: List<Tsunami>
)

data class Field(
    @SerializedName("Fieldid")
    val Fieldid: String,
    @SerializedName("type")
    val type: String
)

data class Tsunami(
    @SerializedName("ReportColor")
    val reportColor: String,
    @SerializedName("ReportContent")
    val reportContent: String,
    @SerializedName("ReportNo")
    val reportNo: String,
    @SerializedName("ReportType")
    val reportType: String,
    @SerializedName("TsunamiNo")
    val tsunamiNo: Int,
    @SerializedName("Web")
    val web: String,
    @Embedded
    @SerializedName("EarthquakeInfo")
    val earthquakeInfo: EarthquakeInfo,
    @Embedded
    @SerializedName("TsunamiWave")
    val tsunamiWave: TsunamiWave
)

data class EarthquakeInfo(
    @SerializedName("OriginTime")
    val originTime: String,
    @SerializedName("Source")
    val source: String,
    @SerializedName("FocalDepth")
    val focalDepth: Double,
    @Embedded
    @SerializedName("Epicenter")
    val epicenter: Epicenter,
    @Embedded
    @SerializedName("EarthquakeMagnitude")
    val earthquakeMagnitude: EarthquakeMagnitude
)
@TypeConverters(MyConverter.ConverterForWarningArea::class, MyConverter.ConverterForTsuStation::class)
data class TsunamiWave(
    @SerializedName("WarningArea")
    val warningArea: List<WarningArea>,
    @SerializedName("TsuStation")
    val tsuStation: List<TsuStation>
)

data class Epicenter(
    @SerializedName("Location")
    val location: String,
    @SerializedName("EpicenterLatitude")
    val epicenterLatitude: Double,
    @SerializedName("EpicenterLongitude")
    val epicenterLongitude: Double
)

data class EarthquakeMagnitude(
    @SerializedName("MagnitudeValue")
    val magnitudeValue: Double
)

data class WarningArea(
    @SerializedName("AreaColor")
    val areaColor: String,
    @SerializedName("AreaDesc")
    val areaDesc: String,
    @SerializedName("AreaName")
    val areaName: String,
    @SerializedName("ArrivalTime")
    val arrivalTime: String,
    @SerializedName("InfoStatus")
    val infoStatus: String,
    @SerializedName("WaveHeight")
    val waveHeight: String
)

data class TsuStation(
    @SerializedName("ArrivalTime")
    val arrivalTime: String,
    @SerializedName("InfoStatus")
    val infoStatus: String,
    @SerializedName("StationID")
    val stationID: String,
    @SerializedName("StationName")
    val stationName: String,
    @SerializedName("StationLatitude")
    val stationLatitude: Double,
    @SerializedName("StationLongitude")
    val stationLongitude: Double,
    @SerializedName("WaveHeight")
    val waveHeight: String
)