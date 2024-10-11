package com.example.walmartapp.di

import com.example.walmartapp.domain.IProductRepository
import com.example.walmartapp.repository.ProductRepositoryImpl
import com.example.walmartapp.repository.ServiceApi
import com.example.walmartapp.repository.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

/**
 * This module is installed in the [SingletonComponent], which ensures that the provided
 * instances will have a singleton scope throughout the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /**
     * This function creates an implementation of the [IProductRepository] interface
     * using the provided [ServiceApi], [ProductDatabase], and [CoroutineDispatcher].
     *
     * @param service The [ServiceApi] instance for making network requests.
     * @param database The [ProductDatabase] instance for accessing local product data.
     * @param dispatcher The [CoroutineDispatcher] used for performing repository operations
     * on the appropriate thread.
     * @return An instance of [IProductRepository].
     */
    @Provides
    fun provideRepository(
        service: ServiceApi,
        database: ProductDatabase,
        dispatcher: CoroutineDispatcher
    ): IProductRepository {
        return ProductRepositoryImpl(service, database, dispatcher)
    }
}