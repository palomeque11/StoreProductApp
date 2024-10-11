package com.example.walmartapp.repository

import com.example.walmartapp.model.Product
import retrofit2.Response
import retrofit2.http.GET

/**
 * Service API interface for accessing product data from the network.
 *
 * This interface defines the API endpoints for retrieving product information.
 * It utilizes Retrofit for making network requests.
 */
interface ServiceApi {
    /**
     * Fetches a list of products from the API.
     *
     * This method makes a GET request to the `/products` endpoint and returns
     * a [Response] object containing a list of [Product] instances.
     *
     * @return A [Response] object containing a list of [Product] or an error response.
     */
    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>
}