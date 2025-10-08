package com.mrappbuilder.productbrowserapp.UseCase

import com.mrappbuilder.productbrowserapp.ApiHandler.ProductRepository


class GetProductByCategory(private val repo: ProductRepository) {
    suspend operator fun invoke(category: String) = repo.getProductbyCategory(category)
}