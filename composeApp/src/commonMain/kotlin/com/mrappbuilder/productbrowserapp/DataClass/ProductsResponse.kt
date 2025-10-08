package com.mrappbuilder.productbrowserapp.DataClass



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    @SerialName("products")   val products: List<ProductDto>,
   // @SerialName("products") val products: List<Product>,
@SerialName("total") val total: Int,
@SerialName("skip") val skip: Int,
@SerialName("limit") val limit: Int
)







