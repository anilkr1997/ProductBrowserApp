package com.mrappbuilder.productbrowserapp.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mrappbuilder.productbrowserapp.DataClass.Category

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
