package com.pg13.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.pg13.data.entities.local.ProfileLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Transaction
    @Query("SELECT * FROM profile")
    abstract fun getProfile(): Flow<List<ProfileLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(query: ProfileLocal)

    @Update
    suspend fun updateProfile(vararg profile: ProfileLocal)
}