package com.shqadri.lokal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shqadri.lokal.domain.models.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: JobEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: JobEntity)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllBookmarks(): Flow<List<JobEntity>>
}
