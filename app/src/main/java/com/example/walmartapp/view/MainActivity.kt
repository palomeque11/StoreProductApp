package com.example.walmartapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartapp.R
import com.example.walmartapp.databinding.ActivityMainBinding
import com.example.walmartapp.utils.ResultState
import com.example.walmartapp.view.adapter.ProductMainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    @VisibleForTesting
    val productAdapter by lazy {
        ProductMainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setting up the RecyclerView for displaying products
        binding.productsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }

        // Observe the product list and update the adapter when data changes
        viewModel.products.observe(this) { products ->
            productAdapter.updateItems(products)
        }

        // Observe the application state to handle loading and error states
        viewModel.appState.observe(this) { state ->
            when (state) {
                is ResultState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.startProgress()
                }
                is ResultState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.progressBar.stopProgress()
                }
                is ResultState.Error -> {
                    Log.e(this.javaClass.name, "onCreate: ${state.error.message}", )
                    Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Initiates the process to update local products from the API
        viewModel.updateLocalProducts()
    }
}