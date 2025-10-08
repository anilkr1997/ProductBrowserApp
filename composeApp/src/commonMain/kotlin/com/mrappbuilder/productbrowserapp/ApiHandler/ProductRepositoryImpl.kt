package com.mrappbuilder.productbrowserapp.ApiHandler

import com.mrappbuilder.productbrowserapp.DataClass.Category
import com.mrappbuilder.productbrowserapp.DataClass.Product
import com.mrappbuilder.productbrowserapp.DataClass.mapper.toDomain


class ProductRepositoryImpl(private val api: ProductApi) : ProductRepository {
    override suspend fun getProducts(): List<Product> = api.getProducts().products.map { it.toDomain() }
    override suspend fun searchProducts(query: String): List<Product> = api.searchProducts(query).products.map { it.toDomain() }
    override suspend fun getProductById(id: Int): Product? = api.getProduct(id).toDomain()
    override suspend fun getProductCategory(): List<Category> = api.getProductCategory()
    override suspend fun getProductbyCategory(category: String): List<Product> = api.getProductByCategory(category).products.map { it.toDomain() }
}
