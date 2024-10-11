package com.example.walmartapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.walmartapp.model.Product

/**
 * Room Database for managing product-related data.
 *
 * @property productDao Access point for product-related database operations.
 */
@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    /**
     * Provides access to the [ProductDao] for product-related database operations.
     *
     * @return An instance of [ProductDao] to perform CRUD operations on products.
     */
    abstract fun productDao(): ProductDao
}