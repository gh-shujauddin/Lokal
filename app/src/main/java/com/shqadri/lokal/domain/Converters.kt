package com.shqadri.lokal.domain

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shqadri.lokal.domain.models.ContentV3ItemEntity
import com.shqadri.lokal.domain.models.JobTagEntity

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromJobTagEntityList(value: List<JobTagEntity>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toJobTagEntityList(value: String): List<JobTagEntity>? {
        val listType = object : TypeToken<List<JobTagEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromContentV3ItemEntityList(value: List<ContentV3ItemEntity>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toContentV3ItemEntityList(value: String): List<ContentV3ItemEntity>? {
        val listType = object : TypeToken<List<ContentV3ItemEntity>>() {}.type
        return gson.fromJson(value, listType)
    }
}
