package com.pg13.data.util

import com.google.gson.annotations.SerializedName
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException


internal inline fun <ResponseType, ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResponseType>,
    crossinline mapper: (ResponseType) -> ResultType,
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading<ResultType>())

    val result = remoteCall.invoke()
    when {
        result.code().toString()
            .startsWith("2") -> emit(Resource.Success<ResultType>(mapper(result.body()!!)))

        result.code() == 422 -> {

            val jObjError = JSONObject(result.errorBody()?.string().toString())
            val error = jObjError.getJSONArray("detail").getJSONObject(0).getString("msg")
            emit(
                Resource.Error<ResultType>(
                    message = error
                )
            )
        }

        result.code() == 400 -> {
            val jObjError = JSONObject(result.errorBody()?.string().toString())
            emit(
                Resource.Error<ResultType>(
                    message = jObjError.getJSONObject("detail").getString("message")
                )
            )
        }

        result.code() == 404 -> emit(Resource.Error<ResultType>(message = result.message()))
        result.code() == 401 -> emit(Resource.Error<ResultType>(message = "Ошибка авторизации"))
        result.code().toString()
            .startsWith("5") -> emit(Resource.Error<ResultType>(message = "Внутренняя ошибка сервера"))

        else -> emit(Resource.Error<ResultType>(exception = HttpException(result)))
    }
}.handleException()

internal inline fun <ResponseType, ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResponseType>,
    crossinline mapper: (ResponseType) -> ResultType,
    crossinline saveResult: suspend (ResultType) -> Unit,
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading<ResultType>())

    val result = remoteCall.invoke()

    when {
        result.code().toString()
            .startsWith("2") -> {
            val data = mapper(result.body()!!)
            emit(Resource.Success<ResultType>(data))
            saveResult(data)
        }

        result.code() == 422 -> {
            val jObjError: JSONObject = JSONObject(result.errorBody()?.string().toString())

            val error = jObjError.getJSONArray("detail").getJSONObject(0).getString("msg")
            emit(
                Resource.Error<ResultType>(
                    message = error
                )
            )
        }

        result.code() == 400 -> {
            val jObjError = JSONObject(result.errorBody()?.string().toString())
            emit(
                Resource.Error<ResultType>(
                    message = jObjError.getJSONObject("detail").getString("message")
                )
            )
        }

        result.code() == 404 -> emit(Resource.Error<ResultType>(message = result.message()))
        result.code() == 401 -> emit(Resource.Error<ResultType>(message = "Ошибка авторизации"))

//        result.code().toString()
//            .startsWith("4") -> emit(Resource.Error<ResultType>(message = mapError(result)))

        result.code().toString()
            .startsWith("5") -> emit(Resource.Error<ResultType>(message = "Внутренняя ошибка сервера"))

        else -> emit(Resource.Error<ResultType>(exception = HttpException(result)))
    }
}.handleException()

fun <ResponseType> mapErrorValidation(response: ResponseType): String {

    return (response as ErrorValidation).detail.msg!!
}

internal inline fun <ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResultType>,
): Flow<Resource<ResultType>> = networkBoundResource(remoteCall) { it }

fun <T> Flow<Resource<T>>.handleException()
        : Flow<Resource<T>> = catch { exception ->
    if (exception is IOException) {
        emit(
            Resource.Error<T>(
                exception = exception,
                message = if (exception is ConnectException) "Не удалось подключиться" else "Ошибка доступа в интернет"
            )
        )
    } else {
        emit(
            Resource.Error<T>(
                exception = exception,
                message = "Неизвестная ошибка\n${exception.message}"
            )
        ) // неизвестная ошибка
    }
}

data class Error(val detail: Detail)
data class ErrorValidation(val detail: Detail, val ctx: Ctx)

data class Ctx(
    val limit_value: Int? = null
)

data class Detail(
    @SerializedName("loc") var loc: ArrayList<String> = arrayListOf(),
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("ctx") var ctx: Ctx? = Ctx()
) {
    override fun toString(): String {
        return "sdjfkajsdhfkjhasdfkhfdashljasdf"
    }
}