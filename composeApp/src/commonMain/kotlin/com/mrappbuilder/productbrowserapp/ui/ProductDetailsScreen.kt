package com.mrappbuilder.productbrowserapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mrappbuilder.productbrowserapp.DataClass.Product
import com.mrappbuilder.productbrowserapp.DataClass.Review
import org.jetbrains.compose.resources.painterResource
import productbrowserapp.composeapp.generated.resources.Res
import productbrowserapp.composeapp.generated.resources.compose_multiplatform
import kotlin.text.ifEmpty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    product: Product,
    onBackClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                onClick = onAddToCart,
//                icon = { Icon(Icons.Default.ShoppingCart, "Add to Cart") },
//                text = { Text("Add to Cart") },
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary
//            )
//        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                // Product Images Carousel
                ProductImageCarousel(images = product.images)
            }

            item {
                // Product Details
                ProductDetailsContent(product = product)
            }

            item {
                // Product Specifications
                ProductSpecificationsGrid(product = product)
            }

            item {
                // Reviews Section
                ReviewsSection(product.reviews)
            }

            item {
                // Shipping & Policies
                ShippingPoliciesSection(product)

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun ProductImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = "Product image $page",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.compose_multiplatform),
                error = painterResource(Res.drawable.compose_multiplatform)
            )
        }

        // Dots indicator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}

@Composable
fun ProductDetailsContent(product: Product) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.brand.ifEmpty { "No Brand" },
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Price and Rating Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Price with discount
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (product.discountPercentage > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFFF4757),
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${product.discountPercentage}% OFF",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

//                if (product.discountPercentage > 0) {
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = "$${"%.2f".format(product.price * (1 + product.discountPercentage / 100))}",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant,
//                        textDecoration = TextDecoration.LineThrough
//                    )
//                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Rating Chip
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFA000),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${product.rating}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Availability Status
        Row(verticalAlignment = Alignment.CenterVertically) {
            val statusColor = when (product.availabilityStatus.lowercase()) {
                "in stock" -> Color(0xFF4CAF50)
                "low stock" -> Color(0xFFFF9800)
                else -> Color(0xFFF44336)
            }

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(statusColor, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${product.availabilityStatus} • ${product.stock} units available",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Description
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.2,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tags
        if (product.tags.isNotEmpty()) {
            Text(
                text = "Tags",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                product.tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun ProductSpecificationsGrid(product: Product) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Specifications",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            // Basic Information
            Text(
                text = "Basic Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DetailRow(title = "SKU", value = product.sku)
            DetailRow(title = "Category", value = product.category)
            DetailRow(title = "Brand", value = product.brand.ifEmpty { "Not specified" })
            DetailRow(title = "Weight", value = "${product.weight}g")
            DetailRow(title = "Dimensions", value = "${product.dimensions.width} × ${product.dimensions.height} × ${product.dimensions.depth} cm")
            DetailRow(title = "Minimum Order Quantity", value = product.minimumOrderQuantity.toString())

            Spacer(modifier = Modifier.height(16.dp))

            // Stock & Availability
            Text(
                text = "Stock & Availability",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DetailRow(title = "Availability Status", value = product.availabilityStatus)
            DetailRow(title = "Stock Quantity", value = product.stock.toString())
            DetailRow(title = "Rating", value = "${product.rating}/5")

            Spacer(modifier = Modifier.height(16.dp))

            // Meta Information
            Text(
                text = "Product Meta",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DetailRow(title = "Barcode", value = product.meta.barcode)
            DetailRow(title = "QR Code", value = product.meta.qrCode)
            DetailRow(title = "Created", value = product.meta.createdAt)
            DetailRow(title = "Last Updated", value = product.meta.updatedAt)
        }
    }
}

@Composable
fun ReviewsSection(reviews: List<Review>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Customer Reviews",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (reviews.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No reviews yet",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            // Average Rating
            val averageRating = reviews.map { it.rating }.average()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Average Rating:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    repeat(5) { index ->
                        Icon(
                            imageVector = if (index < averageRating.toInt()) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = "Rating",
                            tint = if (index < averageRating.toInt()) Color(0xFFFFA000) else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(${reviews.size} reviews)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reviews List - Use Column instead of LazyColumn
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                reviews.forEach { review ->
                    ReviewItem(review = review)
                }
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Review header with rating and date
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Rating stars
                Row {
                    repeat(5) { index ->
                        Icon(
                            imageVector = if (index < review.rating) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = "Rating",
                            tint = if (index < review.rating) Color(0xFFFFA000) else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = review.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Reviewer info
            Text(
                text = review.reviewerName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = review.reviewerEmail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Review comment
            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.3
            )
        }
    }
}

@Composable
fun ShippingPoliciesSection(product: Product) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Shipping & Policies",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            // Shipping Information
            Text(
                text = "Shipping Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = product.shippingInformation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Warranty Information
            Text(
                text = "Warranty",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = product.warrantyInformation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Return Policy
            Text(
                text = "Return Policy",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = product.returnPolicy,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.3
            )
        }
    }
}

@Composable
fun DetailRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}
