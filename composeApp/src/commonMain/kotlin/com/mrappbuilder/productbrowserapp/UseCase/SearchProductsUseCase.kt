package com.mrappbuilder.productbrowserapp.UseCase

import com.mrappbuilder.productbrowserapp.ApiHandler.ProductRepository


class SearchProductsUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(query: String) = repo.searchProducts(query)
}
