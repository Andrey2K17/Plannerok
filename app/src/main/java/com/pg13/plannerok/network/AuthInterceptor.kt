package com.pg13.plannerok.network

import android.util.Log
import com.pg13.domain.usecases.PrefDataSourceUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val prefDataSourceUseCase: PrefDataSourceUseCase,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalResponse = chain.proceed(originalRequest)

        val encodedPathSegments = originalRequest.url.encodedPathSegments

        if (!encodedPathSegments.contains("send-auth-code") && !encodedPathSegments.contains("check-auth-code")
            && !encodedPathSegments.contains("register")
        ) {
            var accessToken = ""
            runBlocking {
                prefDataSourceUseCase.getAuthData().collect {
                    accessToken = it.accessToken ?: ""
                }
            }
            val request = chain.request().newBuilder()
            if (accessToken.isNotEmpty()) {
                request.addHeader("Authorization", "Bearer $accessToken")
            }
            val requestBuild = request.build()
            Log.d("test123", "addHeader: ${requestBuild.headers}")
            return chain.proceed(requestBuild)
        }
        Log.d("test123", "notAddHeader: ${originalRequest.headers}")


        return originalResponse
    }
}