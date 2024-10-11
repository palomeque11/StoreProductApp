package com.example.walmartapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartapp.domain.IProductRepository
import com.example.walmartapp.model.Product
import com.example.walmartapp.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IProductRepository
) : ViewModel() {

    /**
     * LiveData for observing the list of products.
     */
    val products: LiveData<List<Product>> = repository.getAllProducts()

    /**
     * LiveData for observing the current application state.
     */
    private val _appState = MutableLiveData<ResultState<List<Product>>>()
    val appState: LiveData<ResultState<List<Product>>> = _appState

    /**
     * Updates the local product data by fetching it from the repository.
     *
     * This function launches a coroutine that collects the state of the product
     * update process from the repository and updates [_appState] accordingly.
     */
    fun updateLocalProducts() {
        viewModelScope.launch {
            repository.updateProducts().collect {
                _appState.postValue(it)
            }
        }
    }
}