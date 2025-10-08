package com.mrappbuilder.productbrowserapp.DataClass.mapper

import com.mrappbuilder.productbrowserapp.DataClass.Product
import com.mrappbuilder.productbrowserapp.DataClass.ProductDto


fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    rating = rating,
    brand = brand,
    category = category,
    discountPercentage = discountPercentage,
    stock = stock,
    tags = tags,
    sku = sku,
    weight = weight,
    dimensions = dimensions,
    warrantyInformation = warrantyInformation,
    shippingInformation = shippingInformation,
    availabilityStatus = availabilityStatus,
    reviews = reviews,
    returnPolicy = returnPolicy,
    minimumOrderQuantity = minimumOrderQuantity,
    meta = meta,
    images = images,
    thumbnail = thumbnail
)
