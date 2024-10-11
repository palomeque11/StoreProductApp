package com.example.walmartapp.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walmartapp.model.Product

/**
 * Data Access Object (DAO) for managing product-related database operations.
 *
 * This interface defines the methods for interacting with the products table
 * in the Room database, providing an abstraction layer for data access.
 */
@Dao
interface ProductDao {
    /**
     * Retrieves all products from the database.
     *
     * @return A [LiveData] object containing a list of all [Product] instances.
     */
    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    /**
     * Inserts a list of products into the database.
     *
     * This method will replace any existing products with the same primary key
     * due to the [OnConflictStrategy.REPLACE] strategy.
     *
     * @param products A list of [Product] instances to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<Product>)
}