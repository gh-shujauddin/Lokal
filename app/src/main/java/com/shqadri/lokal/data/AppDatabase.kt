package com.shqadri.lokal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shqadri.lokal.domain.Converters
import com.shqadri.lokal.domain.models.JobEntity

@Database(
    entities = [JobEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
