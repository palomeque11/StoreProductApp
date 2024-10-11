package com.example.walmartapp.domain

import androidx.lifecycle.LiveData
import com.example.walmartapp.model.Product
import com.example.walmartapp.utils.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * This interface defines the methods to retrieve and update products, ensuring a separation
 * of concerns between data sources and the rest of the application.
 */
interface IProductRepository {
    /**
     * Method provides a reactive stream of product data, allowing UI components to
     * observe changes to the product list.
     *
     * @return A [LiveData] object containing a list of [Product] instances.
     */
    fun getAllProducts(): LiveData<List<Product>>

    /**
     * Updates the list of products from a remote source.
     *
     * @return A [Flow] emitting [ResultState] containing a list of [Product] instances
     *         or an error state.
     */
    fun updateProducts(): Flow<ResultState<List<Product>>>
}