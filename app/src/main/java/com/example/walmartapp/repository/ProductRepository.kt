package com.example.walmartapp.repository

import androidx.lifecycle.LiveData
import com.example.walmartapp.domain.IProductRepository
import com.example.walmartapp.model.Product
import com.example.walmartapp.repository.database.ProductDatabase
import com.example.walmartapp.utils.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val productDatabase: ProductDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : IProductRepository {
    override fun getAllProducts(): LiveData<List<Product>> = productDatabase.productDao().getAllProducts()

    override fun updateProducts(): Flow<ResultState<List<Product>>> = flow {
        emit(ResultState.Loading)

        try {
            val response = serviceApi.getProducts()
            if (response.isSuccessful) {
                response.body()?.let { products ->
                    emit(ResultState.Success(products))
                    productDatabase.productDao().insertProducts(products)
                }
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }.flowOn(ioDispatcher)
}