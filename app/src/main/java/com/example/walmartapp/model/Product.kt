package com.example.walmartapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a product entity in the database.
 *
 * @property id Unique identifier for the product. This serves as the primary key in the database.
 * @property title The title or name of the product.
 * @property price The price of the product.
 * @property description A brief description of the product.
 */
@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String
)