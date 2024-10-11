package com.example.walmartapp.di

import com.example.walmartapp.repository.ServiceApi
import com.example.walmartapp.utils.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This module is installed in the [SingletonComponent], which ensures that the provided
 * instances will have a singleton scope throughout the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * This function builds a Retrofit instance configured with the base URL and a Gson
     * converter for JSON serialization.
     *
     * @param okHttpClient The [OkHttpClient] instance used for handling HTTP requests.
     * @param gson The [Gson] instance used for JSON conversion.
     * @return An instance of [ServiceApi] for making API calls.
     */
    @Provides
    fun providesServiceApi(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ServiceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ServiceApi::class.java)

    /**
     * This function creates an [OkHttpClient] instance that includes logging capabilities
     * via the provided [HttpLoggingInterceptor].
     *
     * @param loggingInterceptor The [HttpLoggingInterceptor] used for logging HTTP requests and responses.
     * @return An instance of [OkHttpClient] configured for network requests.
     */
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    /**
     * This interceptor logs the details of HTTP requests and responses for debugging purposes.
     *
     * @return An instance of [HttpLoggingInterceptor] configured to log the body of HTTP messages.
     */
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * This function creates a [Gson] instance that can be used throughout the application for
     * converting objects to and from JSON.
     *
     * @return An instance of [Gson].
     */
    @Provides
    fun providesGson(): Gson = Gson()

    /**
     * This function returns the IO dispatcher to be used for network and database operations.
     *
     * @return An instance of [CoroutineDispatcher] configured for IO tasks.
     */
    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO
}