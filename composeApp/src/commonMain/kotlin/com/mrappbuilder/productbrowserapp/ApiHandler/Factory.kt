package com.mrappbuilder.productbrowserapp.ApiHandler


import com.mrappbuilder.productbrowserapp.UseCase.GetProductByCategory
import com.mrappbuilder.productbrowserapp.UseCase.GetProductCategory
import com.mrappbuilder.productbrowserapp.UseCase.GetProductsUseCase
import com.mrappbuilder.productbrowserapp.UseCase.SearchProductsUseCase
import com.mrappbuilder.productbrowserapp.ViewModels.ProductsViewModel
import com.mrappbuilder.productbrowserapp.httpClientEngine
import io.ktor.client.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

//expect fun httpClientEngine(): HttpClientEngine

fun createProductsViewModel(): ProductsViewModel {
    val client = HttpClient(httpClientEngine()) {

        install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
           json(Json {
               ignoreUnknownKeys = true       // don't fail on unknown fields
               coerceInputValues = true       // coerce missing/malformed primitives to defaults when possible
           })
        }
        install(io.ktor.client.plugins.logging.Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }
    }
    val api = ProductApiImpl(client)
    val repo = ProductRepositoryImpl(api)
    val getProducts = GetProductsUseCase(repo)
    val search = SearchProductsUseCase(repo)
    val categary = GetProductCategory(repo)
    val producetbycategory = GetProductByCategory(repo)
    return ProductsViewModel(getProducts,search,categary,producetbycategory)
}
