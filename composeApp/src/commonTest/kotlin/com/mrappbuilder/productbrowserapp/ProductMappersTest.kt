package com.mrappbuilder.productbrowserapp

import com.mrappbuilder.productbrowserapp.DataClass.Dimensions
import com.mrappbuilder.productbrowserapp.DataClass.Meta
import com.mrappbuilder.productbrowserapp.DataClass.ProductDto
import com.mrappbuilder.productbrowserapp.DataClass.Review
import kotlin.test.Test
import kotlin.test.assertEquals


class ProductDtoMappingTest {

    @Test
    fun `dto to product mapping without nulls`() {
        val dto = ProductDto(
            id = 42,
            title = "Test Item",
            description = "Desc",
            category = "test-cat",
            price = 199.99,
            discountPercentage = 5.0,
            rating = 4.8,
            stock = 12,
            tags = listOf("t1", "t2"),
            brand = "BrandX",
            sku = "SKU-42",
            weight = 500,
            dimensions = Dimensions(width = 10.0, height = 5.0, depth = 2.0),
            warrantyInformation = "1 year",
            shippingInformation = "Ships worldwide",
            availabilityStatus = "in_stock",
            reviews = listOf(Review(5, "Great", "2025-01-01", "Alice", "a@x.com")),
            returnPolicy = "30 days",
            minimumOrderQuantity = 1,
            meta = Meta(
                createdAt = "2025-01-01",
                updatedAt = "2025-01-02",
                barcode = "123",
                qrCode = "q"
            ),
            images = listOf("img1.jpg"),
            thumbnail = "thumb.jpg"
        )

        val product = dto

        assertEquals(42, product.id)
        assertEquals("Test Item", product.title)
        assertEquals("BrandX", product.brand)
        assertEquals("thumb.jpg", product.thumbnail)
        assertEquals(1, product.images.size)
        assertEquals(10.0, product.dimensions.width)
        assertEquals(5.0, product.dimensions.height)
        assertEquals(2.0, product.dimensions.depth)
        assertEquals(199.99, product.price)
        assertEquals(5.0, product.discountPercentage)
        assertEquals(4.8, product.rating)
        assertEquals(12, product.stock)
        assertEquals(listOf("t1", "t2"), product.tags)
        assertEquals("SKU-42", product.sku)
        assertEquals(500, product.weight)
        assertEquals("Desc", product.description)
        assertEquals("test-cat", product.category)
        assertEquals("1 year", product.warrantyInformation)
        assertEquals("Ships worldwide", product.shippingInformation)
        assertEquals("in_stock", product.availabilityStatus)
        assertEquals(1, product.reviews.size)
        assertEquals("Great", product.reviews[0].comment)
        assertEquals("30 days", product.returnPolicy)
        assertEquals(1, product.minimumOrderQuantity)
        assertEquals("2025-01-01", product.meta.createdAt)
        assertEquals("2025-01-02", product.meta.updatedAt)
        assertEquals("123", product.meta.barcode)
        assertEquals("q", product.meta.qrCode)

    }
}

