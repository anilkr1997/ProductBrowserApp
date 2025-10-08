package com.mrappbuilder.productbrowserapp.ApiHandler


import com.mrappbuilder.productbrowserapp.DataClass.Category
import com.mrappbuilder.productbrowserapp.DataClass.ProductDto
import com.mrappbuilder.productbrowserapp.DataClass.ProductsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

interface ProductApi {
    suspend fun getProducts(limit: Int = 30): ProductsResponse
    suspend fun searchProducts(q: String): ProductsResponse
    suspend fun getProduct(id: Int): ProductDto
    suspend fun getProductCategory(): List<Category>
    suspend fun getProductByCategory(category: String): ProductsResponse
}

class ProductApiImpl(private val client: HttpClient, private val baseUrl: String = "https://dummyjson.com") : ProductApi {
    override suspend fun getProducts(limit: Int): ProductsResponse =
        client.get("$baseUrl/products").body()

    override suspend fun searchProducts(q: String): ProductsResponse =
        client.get("$baseUrl/products/search") { parameter("q", q) }.body()

    override suspend fun getProduct(id: Int): ProductDto =
        client.get("$baseUrl/products/$id").body()

    override suspend fun getProductCategory(): List<Category> =
        client.get("$baseUrl/products/categories").body()

    override suspend fun getProductByCategory(category: String): ProductsResponse = client.get("$baseUrl/products/category/${category}").body()
}
