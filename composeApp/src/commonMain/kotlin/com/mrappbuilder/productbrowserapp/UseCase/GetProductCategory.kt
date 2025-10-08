package com.mrappbuilder.productbrowserapp.UseCase

import com.mrappbuilder.productbrowserapp.ApiHandler.ProductRepository


class GetProductCategory(private val repo: ProductRepository) {
    suspend operator fun invoke() = repo.getProductCategory()
}