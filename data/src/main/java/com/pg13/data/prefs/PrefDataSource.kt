package com.pg13.data.prefs

import android.content.Context
import androidx.datastore.dataStore
import com.pg13.data.entities.dataSource.AuthData
import kotlinx.coroutines.flow.Flow


class PrefDataSource(private val context: Context) {
    private val Context.dataStore by dataStore("auth-data.json", AuthDataSerializer)

    val exampleCounterFlow: Flow<AuthData> = context.dataStore.data

    suspend fun setCounter(authData: AuthData) {
        context.dataStore.updateData { currentAuthData ->
            currentAuthData.copy(
                refreshToken = authData.refreshToken,
                accessToken = authData.accessToken
            )
        }
    }
}