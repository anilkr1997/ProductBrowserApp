package com.mrappbuilder.productbrowserapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrappbuilder.productbrowserapp.DataClass.Category

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
