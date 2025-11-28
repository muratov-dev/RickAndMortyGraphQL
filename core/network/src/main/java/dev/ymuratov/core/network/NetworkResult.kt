package dev.ymuratov.core.network

import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.exception.ApolloException

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val message: String) : NetworkResult<Nothing>
}

suspend fun <T : Operation.Data> safeQuery(block: suspend () -> ApolloResponse<T>): NetworkResult<T> {
    return try {
        val response = block()

        if (response.errors?.isNotEmpty() == true){
            return NetworkResult.Error(response.errors!!.first().message)
        }

        val data = response.data ?: return NetworkResult.Error("NO DATA")

        NetworkResult.Success(data)
    } catch (e: ApolloException) {
        NetworkResult.Error(e.message ?: "NETWORK ERROR")
    }
}