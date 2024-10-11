package com.example.walmartapp.di

import android.content.Context
import androidx.room.Room
import com.example.walmartapp.repository.database.ProductDao
import com.example.walmartapp.repository.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This module is installed in the [SingletonComponent], which means that the provided
 * database instance will live as long as the application does.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Provides a singleton instance of [ProductDatabase].
     *
     * This function creates a Room database instance for accessing product data.
     *
     * @param context The application context used to create the database instance.
     * @return An instance of [ProductDatabase].
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_database"
        ).build()
    }
}