package com.pg13.data.api

import com.pg13.data.entities.remote.CheckAuthCodeBody
import com.pg13.data.entities.remote.CheckAuthCodeRemote
import com.pg13.data.entities.remote.IsSuccessRemote
import com.pg13.data.entities.remote.PhoneBody
import com.pg13.data.entities.remote.ProfileDataRemote
import com.pg13.data.entities.remote.RefreshTokenBody
import com.pg13.data.entities.remote.RegisterDataBody
import com.pg13.data.entities.remote.RegisterDataRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserClient {

    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body phoneBody: PhoneBody
    ): Response<IsSuccessRemote>

    @POST("/api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body body: CheckAuthCodeBody
    ): Response<CheckAuthCodeRemote>

    @POST("/api/v1/users/register/")
    suspend fun register(
        @Body body: RegisterDataBody
    ): Response<RegisterDataRemote>

    @POST("/api/v1/users/refresh-token/")
    suspend fun refreshToken(
        @Body body: RefreshTokenBody
    ): Response<RegisterDataRemote>

    @GET("/api/v1/users/me/")
    suspend fun getCurrentUser(): Response<ProfileDataRemote>
}