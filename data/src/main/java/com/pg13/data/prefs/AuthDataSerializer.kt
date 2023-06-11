package com.pg13.data.prefs

import androidx.datastore.core.Serializer
import com.pg13.data.entities.dataSource.AuthData
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AuthDataSerializer: Serializer<AuthData> {
    override val defaultValue: AuthData
        get() = AuthData()

    override suspend fun readFrom(input: InputStream): AuthData {
        return try {
            Json.decodeFromString(
                deserializer = AuthData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AuthData, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AuthData.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}