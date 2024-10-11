package com.example.walmartapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartapp.databinding.ProductItemBinding
import com.example.walmartapp.model.Product

class ProductMainAdapter (
    private val products: MutableList<Product> = mutableListOf()
) : RecyclerView.Adapter<ItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<Product>) {
        products.clear()
        products.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bindCharacter(products[position])
}

class ItemViewHolder(
    private val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindCharacter(item: Product) {
        binding.productTitle.text = item.title
        binding.productPrice.text = "$${item.price}"
    }
}