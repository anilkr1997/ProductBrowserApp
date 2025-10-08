package com.mrappbuilder.productbrowserapp

 import androidx.compose.animation.ExperimentalAnimationApi
 import androidx.compose.animation.AnimatedVisibility
 import androidx.compose.animation.animateColorAsState
 import androidx.compose.animation.animateContentSize
 import androidx.compose.animation.core.FastOutSlowInEasing
 import androidx.compose.animation.core.RepeatMode
 import androidx.compose.animation.core.animateDpAsState
 import androidx.compose.animation.core.animateFloat
 import androidx.compose.animation.core.animateFloatAsState
 import androidx.compose.animation.core.infiniteRepeatable
 import androidx.compose.animation.core.rememberInfiniteTransition
 import androidx.compose.animation.core.tween
 import androidx.compose.animation.fadeIn
 import androidx.compose.animation.fadeOut
 import androidx.compose.animation.scaleIn
 import androidx.compose.animation.scaleOut
 import androidx.compose.animation.slideInHorizontally
 import androidx.compose.animation.slideInVertically
 import androidx.compose.animation.slideOutHorizontally
 import androidx.compose.animation.slideOutVertically
 import androidx.compose.foundation.BorderStroke
 import androidx.compose.foundation.background
 import androidx.compose.foundation.border
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.gestures.detectTapGestures
 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.FlowRow
 import androidx.compose.foundation.layout.PaddingValues
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.height
 import androidx.compose.foundation.layout.heightIn
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.layout.size
 import androidx.compose.foundation.layout.width
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.lazy.LazyRow
 import androidx.compose.foundation.lazy.grid.GridCells
 import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
 import androidx.compose.foundation.lazy.grid.items
 import androidx.compose.foundation.lazy.items
 import androidx.compose.foundation.pager.HorizontalPager
 import androidx.compose.foundation.pager.rememberPagerState
 import androidx.compose.foundation.rememberScrollState
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.foundation.text.BasicTextField
 import androidx.compose.foundation.text.KeyboardActions
 import androidx.compose.foundation.text.KeyboardOptions
 import androidx.compose.foundation.verticalScroll
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.automirrored.filled.ArrowBack
 import androidx.compose.material.icons.automirrored.filled.Assignment
 import androidx.compose.material.icons.filled.Add
 import androidx.compose.material.icons.filled.ArrowBack
 import androidx.compose.material.icons.filled.Assignment
 import androidx.compose.material.icons.filled.Check
 import androidx.compose.material.icons.filled.Close
 import androidx.compose.material.icons.filled.Favorite
 import androidx.compose.material.icons.filled.FavoriteBorder
 import androidx.compose.material.icons.filled.Inventory2
 import androidx.compose.material.icons.filled.LocalShipping
 import androidx.compose.material.icons.filled.Person
 import androidx.compose.material.icons.filled.QrCode
 import androidx.compose.material.icons.filled.Remove
 import androidx.compose.material.icons.filled.Search
 import androidx.compose.material.icons.filled.Share
 import androidx.compose.material.icons.filled.ShoppingCart
 import androidx.compose.material.icons.filled.Star
 import androidx.compose.material.icons.filled.StarBorder
 import androidx.compose.material.icons.filled.SwapHoriz
 import androidx.compose.material.icons.filled.Tune
 import androidx.compose.material3.Badge
 import androidx.compose.material3.BadgedBox
 import androidx.compose.material3.Button
 import androidx.compose.material3.ButtonDefaults
 import androidx.compose.material3.Card
 import androidx.compose.material3.CardDefaults
 import androidx.compose.material3.CircularProgressIndicator
 import androidx.compose.material3.ExperimentalMaterial3Api

 import androidx.compose.material3.ExtendedFloatingActionButton
 import androidx.compose.material3.Icon
 import androidx.compose.material3.IconButton
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.OutlinedButton
 import androidx.compose.material3.Scaffold
 import androidx.compose.material3.Surface
 import androidx.compose.material3.Text
 import androidx.compose.material3.TopAppBar
 import androidx.compose.material3.TopAppBarDefaults
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.LaunchedEffect
 import androidx.compose.runtime.SideEffect
 import androidx.compose.runtime.collectAsState
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.draw.scale
 import androidx.compose.ui.draw.shadow
 import androidx.compose.ui.geometry.Offset
 import androidx.compose.ui.graphics.Brush
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.graphics.vector.ImageVector
 import androidx.compose.ui.input.pointer.pointerInput
 import androidx.compose.ui.layout.ContentScale
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.input.ImeAction
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.text.style.TextDecoration
 import androidx.compose.ui.text.style.TextOverflow
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import coil3.compose.AsyncImage
 import com.mrappbuilder.productbrowserapp.DataClass.Category
 import com.mrappbuilder.productbrowserapp.DataClass.Product
 import com.mrappbuilder.productbrowserapp.DataClass.Review
 import com.mrappbuilder.productbrowserapp.ViewModels.ProductsViewModel
 import org.jetbrains.compose.resources.painterResource
 import org.jetbrains.compose.ui.tooling.preview.Preview
 import productbrowserapp.composeapp.generated.resources.Res
 import productbrowserapp.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Preview
fun App(vm: ProductsViewModel) {
    val products by vm.products.collectAsState()
    val category by vm.productscategery.collectAsState()
    val loading by vm.isLoading.collectAsState()
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    SideEffect {

    vm.load()
    }
    // Gradient background for the entire app
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        // Animated visibility for product details
        AnimatedVisibility(
            visible = selectedProduct != null,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
        ) {
            selectedProduct?.let { product ->
                ProductDetailsScreen(
                    product = product,
                    onBackClick = { selectedProduct = null },
                    onAddToCart = { /* Handle add to cart */ }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedProduct == null,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            MainProductList(
                products = products,
                category = category,
                loading = loading,
                onProductClick = { product -> selectedProduct = product

                                 },
                onSearch = { query ->
                    vm.search(query)
                },
                onCategory = {
                    vm.productbycategory(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainProductList(
    products: List<Product>,
    category: List<Category>,
    loading: Boolean,
    onProductClick: (Product) -> Unit,
    onSearch: (String) -> Unit,
    onCategory: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = scrollState.value > 100,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Surface(
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TopAppBar(
                        title = {
                            Text(
                                "Revest",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            titleContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        actions = {
                            IconButton(onClick = { /* Cart */ }) {
                                BadgedBox(badge = {
                                    if (/* cart count > 0 */ true) {
                                        Badge {
                                            Text("3", style = MaterialTheme.typography.labelSmall)
                                        }
                                    }
                                }) {
                                    Icon(
                                        Icons.Default.ShoppingCart,
                                        contentDescription = "Cart"
                                    )
                                }
                            }
                            IconButton(onClick = { /* Profile */ }) {
                                Icon(Icons.Default.Person, contentDescription = "Profile")
                            }
                        }
                    )
                }
            }
        },
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                onClick = { /* Handle cart/favorites */ },
//                icon = {
//                    Icon(
//                        Icons.Default.ShoppingCart,
//                        "Cart",
//                        modifier = Modifier.size(20.dp)
//                    )
//                },
//                text = { Text("View Cart") },
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier.animateContentSize()
//            )
//        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Hero Section with Search
            HeroSearchSection(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = onSearch
            )

            // Categories Chip Group
            ProductCategories(category,onCategory = onCategory)

            if (loading) {
                ShimmerProductGrid()
            } else {
                AnimatedProductGrid(
                    products = products,
                    onProductClick = onProductClick
                )
            }
        }
    }
}

@Composable
fun HeroSearchSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primaryContainer
                    )
                )
            )
            .padding(20.dp)
    ) {
        // Welcome Text
        Text(
            text = "Discover Amazing\nProducts",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Find the best products at great prices",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Enhanced Search Bar
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    var isActive by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(16.dp, RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.weight(1f),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            "Search products...",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    innerTextField()
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearch(query) }
                )
            )

            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                IconButton(
                    onClick = {
                        onQueryChange("")
                        onSearch("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { onSearch(query)
                          },
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = "Filter",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun ProductCategories(categories: List<Category>,onCategory: (String) -> Unit) {
    //val categories = listOf("All", "Electronics", "Fashion", "Home", "Beauty", "Sports")
    var selectedCategory by remember { mutableStateOf("All") }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            CategoryChip(
                category = category,
                isSelected = category.name == selectedCategory,
                onClick = {
                    selectedCategory = category.name
                    onCategory(category.slug)

                }
            )
        }
    }
}

@Composable
fun CategoryChip(category: Category, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surfaceVariant,
        label = "chipColor"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "contentColor"
    )

    // Animation for scale and elevation
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        label = "scaleAnimation"
    )

    val elevation by animateDpAsState(
        targetValue = if (isSelected) 8.dp else 2.dp,
        label = "elevationAnimation"
    )

    Card(
        modifier = Modifier
            .clickable { onClick() }
            .height(40.dp)
            .scale(scale)
            .shadow(
                elevation = if (isSelected) 8.dp else 2.dp,
                shape = MaterialTheme.shapes.medium,
                clip = false
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Optional: Add a subtle icon for selected state
//            if (isSelected) {
//                Icon(
//                    imageVector = Icons.Default.Check,
//                    contentDescription = "Selected",
//                    tint = contentColor,
//                    modifier = Modifier
//                        .size(16.dp)
//                        .align(Alignment.CenterEnd)
//                )
//            }

        }
    }
}

@Composable
fun AnimatedProductGrid(
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products, key = { it.id }) { product ->
            AnimatedProductCard(
                product = product,
                onClick = { onProductClick(product)
                    print("produced item $product")
                }
            )
        }
    }
}

@Composable
fun AnimatedProductCard(product: Product, onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }

    val cardElevation by animateDpAsState(
        targetValue = if (isPressed) 12.dp else 4.dp,
        label = "cardElevation"
    )

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        label = "cardScale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onClick()
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false

                    }
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                // Product Image with Shimmer
                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(Res.drawable.compose_multiplatform),
                    error = painterResource(Res.drawable.compose_multiplatform)
                )

                // Discount Badge
                if (product.discountPercentage > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
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

                // Favorite Button
                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            shape = CircleShape
                        )
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color(0xFFFF4757) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.heightIn(min = 40.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Price Section
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (product.discountPercentage > 0) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$${(product.price * (1 + product.discountPercentage / 100)).toInt()}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Rating and Category
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Rating
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFA000),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${product.rating}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Add to Cart Button
                    IconButton(
                        onClick = { /* Add to cart */ },
                        modifier = Modifier
                            .size(28.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerProductGrid() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(6) {
            ShimmerProductCard()
        }
    }
}

@Composable
fun ShimmerProductCard() {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim - 500, 0f),
        end = Offset(translateAnim, 0f)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Shimmer Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(brush)
                    .clip(MaterialTheme.shapes.large)
            )

            Column(modifier = Modifier.padding(12.dp)) {
                // Shimmer Text
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(brush)
                        .clip(MaterialTheme.shapes.small)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(14.dp)
                        .background(brush)
                        .clip(MaterialTheme.shapes.small)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(12.dp)
                            .background(brush)
                            .clip(MaterialTheme.shapes.small)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(brush)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}
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




