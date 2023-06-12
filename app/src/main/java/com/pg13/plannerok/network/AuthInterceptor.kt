package com.pg13.plannerok.network

import com.pg13.domain.usecases.PrefDataSourceUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val prefDataSourceUseCase: PrefDataSourceUseCase,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val encodedPathSegments = originalRequest.url.encodedPathSegments

        return chain.proceed(
            chain.request()
                .newBuilder()
                .apply {
                    if (!encodedPathSegments.contains("send-auth-code") && !encodedPathSegments.contains("check-auth-code")
                        && !encodedPathSegments.contains("register")) {
                        val accessToken = runBlocking { prefDataSourceUseCase.getAuthData().firstOrNull()?.accessToken }
                        if (accessToken != null) addHeader("Authorization", "Bearer $accessToken")
                    }
                }
                .build()
        )
    }
}