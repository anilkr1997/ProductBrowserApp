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
 import androidx.compose.runtime.snapshotFlow
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
 import com.mrappbuilder.productbrowserapp.ui.AppTheme
 import com.mrappbuilder.productbrowserapp.ui.MainProductList
 import com.mrappbuilder.productbrowserapp.ui.ProductDetailsScreen
 import kotlinx.coroutines.FlowPreview
 import kotlinx.coroutines.flow.debounce
 import kotlinx.coroutines.flow.distinctUntilChanged
 import org.jetbrains.compose.resources.painterResource
 import org.jetbrains.compose.ui.tooling.preview.Preview
 import productbrowserapp.composeapp.generated.resources.Res
 import productbrowserapp.composeapp.generated.resources.compose_multiplatform

@FlowPreview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Preview
fun App(vm: ProductsViewModel) {
    AppTheme {
        val products by vm.products.collectAsState()
        val category by vm.productscategery.collectAsState()
        val loading by vm.isLoading.collectAsState()
        var searchqure by remember { mutableStateOf("") }

        var selectedProduct by remember { mutableStateOf<Product?>(null) }


        LaunchedEffect(Unit) {
            vm.load()
        }
        LaunchedEffect(Unit) {
            vm.errorFlow.collect { message ->
                // Handle error message
                println("Massage: $message")
            }
        }
        LaunchedEffect(Unit){
            snapshotFlow { searchqure }.debounce(500).distinctUntilChanged().collect {
                vm.search(it)
            }
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
                    onProductClick = { product -> selectedProduct = product },
                    onSearch = { query ->

                       searchqure = query
                    },
                    onCategory = {
                        vm.productbycategory(it)
                    }
                )
            }
        }
    }
}

















