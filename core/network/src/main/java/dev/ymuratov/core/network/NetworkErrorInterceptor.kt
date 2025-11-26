package dev.ymuratov.core.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.UnknownHostException

class NetworkErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (e: UnknownHostException) {
            throw NoInternetException("NO INTERNET CONNECTION", e)
        } catch (e: IOException) {
            throw NetworkException("NETWORK ERROR", e)
        }
    }
}

class NoInternetException(message: String, cause: Throwable) : IOException(message, cause)
class NetworkException(message: String, cause: Throwable) : IOException(message, cause)
