package com.pg13.plannerok.network

import android.util.Log
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.entities.Resource
import com.pg13.domain.usecases.PrefDataSourceUseCase
import com.pg13.domain.usecases.RefreshTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider


class AuthAuthenticator @Inject constructor(
    private val prefDataSourceUseCase: PrefDataSourceUseCase,
    private val refreshTokenUseCase: Provider<RefreshTokenUseCase>
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.d("test123", "AuthAuthenticator")
        var refreshToken = ""
        var accessToken = ""
        runBlocking {
            prefDataSourceUseCase.getAuthData().collect {
                refreshToken = it.refreshToken ?: ""
            }
        }

        return runBlocking {
            refreshTokenUseCase.get().invoke(refreshToken).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d("test123", "error")
                    }

                    is Resource.Loading -> {
                        Log.d("test123", "loading")
                    }

                    is Resource.Success -> {
                        Log.d("test123", "success: ${resource.data}")
                        prefDataSourceUseCase.setAuthData(
                            AuthDataDomain(
                                resource.data.refreshToken,
                                resource.data.accessToken
                            )
                        )

                        accessToken = resource.data.accessToken
                    }
                }
            }

            if (accessToken.isNotBlank()) {
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${accessToken}")
                    .build()
            } else null
        }
    }

}