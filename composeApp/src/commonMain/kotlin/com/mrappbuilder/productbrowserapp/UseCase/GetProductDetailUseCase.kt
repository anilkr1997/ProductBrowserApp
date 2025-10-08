package com.mrappbuilder.productbrowserapp.UseCase

import com.mrappbuilder.productbrowserapp.ApiHandler.ProductRepository


class GetProductDetailUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(id: Int) = repo.getProductById(id)
}
