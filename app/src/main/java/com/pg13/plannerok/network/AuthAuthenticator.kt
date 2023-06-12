package com.pg13.plannerok.network

import com.pg13.data.api.UserClient
import com.pg13.data.entities.remote.RefreshTokenBody
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.usecases.PrefDataSourceUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider


class AuthAuthenticator @Inject constructor(
    private val prefDataSourceUseCase: PrefDataSourceUseCase,
    private val userClient: Provider<UserClient>
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

        var authData = runBlocking {
            prefDataSourceUseCase.getAuthData().first()
        }

         return runBlocking {
            val newAccessToken = userClient.get().refreshToken(RefreshTokenBody(authData.refreshToken!!))

             if (!newAccessToken.isSuccessful || newAccessToken.body() == null) {
                 prefDataSourceUseCase.setAuthData(AuthDataDomain("", ""))
             }

             newAccessToken.body()?.let {
                 prefDataSourceUseCase.setAuthData(AuthDataDomain(authData.refreshToken, authData.accessToken))
                 response.request.newBuilder()
                     .header("Authorization", "Bearer ${it.accessToken}")
                     .build()
             }
        }
    }
}