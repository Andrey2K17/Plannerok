package com.pg13.domain.entities

data class UpdateUserBodyDomain(
    val name: String,
    val userName: String,
    val birthday: String?,
    val city: String?,
    val vk: String?,
    val instagram: String?,
    val status: String?,
    val avatar: AvatarBodyDomain?,
)