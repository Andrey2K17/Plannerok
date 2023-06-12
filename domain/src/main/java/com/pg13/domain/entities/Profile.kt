package com.pg13.domain.entities

data class Profile(
    val name: String,
    val userName: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val id: Int,
    val last: String,
    val online: Boolean,
    val created: String,
    val phone: String,
    val completedTask: Int,
    val avatars: Avatars,
)
