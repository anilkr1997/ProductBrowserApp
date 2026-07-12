package com.mrappbuilder.productbrowserapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mrappbuilder.productbrowserapp.DataClass.Category
import com.mrappbuilder.productbrowserapp.DataClass.Product

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
