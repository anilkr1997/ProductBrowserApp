package com.mrappbuilder.productbrowserapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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