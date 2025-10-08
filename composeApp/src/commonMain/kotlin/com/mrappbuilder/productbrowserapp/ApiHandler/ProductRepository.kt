package com.mrappbuilder.productbrowserapp.ApiHandler

import com.mrappbuilder.productbrowserapp.DataClass.Category
import com.mrappbuilder.productbrowserapp.DataClass.Product


interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun searchProducts(query: String): List<Product>
    suspend fun getProductById(id: Int): Product?
    suspend fun getProductCategory(): List<Category>
    suspend fun getProductbyCategory(category: String): List<Product>
}
