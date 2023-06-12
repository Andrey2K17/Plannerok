package com.pg13.data.entities.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class ProfileLocal(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val last: String,
    val online: Boolean,
    val created: String,
    val phone: String,
    @ColumnInfo("completed_task") val completedTask: Int
)