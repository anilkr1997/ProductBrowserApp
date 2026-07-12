package com.mrappbuilder.productbrowserapp.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme

// Modern Purple-Blue Theme Colors
object AppColors {
    // Primary Color (Deep Purple/Blue)
    val primary = Color(0xFF6366F1)  // Indigo
    val onPrimary = Color(0xFFFFFFFF)
    val primaryContainer = Color(0xFFEEF0FF)
    val onPrimaryContainer = Color(0xFF1B0E5C)

    // Secondary Color (Violet)
    val secondary = Color(0xFF7C6FE1)  // Soft Purple
    val onSecondary = Color(0xFFFFFFFF)
    val secondaryContainer = Color(0xFFF1EBFF)
    val onSecondaryContainer = Color(0xFF2C1E5C)

    // Tertiary Color (Teal)
    val tertiary = Color(0xFF06B6D4)  // Cyan
    val onTertiary = Color(0xFFFFFFFF)
    val tertiaryContainer = Color(0xFFCFFAFF)
    val onTertiaryContainer = Color(0xFF003B45)

    // Error Color
    val error = Color(0xFFFF4757)
    val onError = Color(0xFFFFFFFF)
    val errorContainer = Color(0xFFFFEBEE)
    val onErrorContainer = Color(0xFF7C0A0F)

    // Background Colors
    val background = Color(0xFFFBF8FF)
    val onBackground = Color(0xFF1C1B1F)
    val surface = Color(0xFFFBF8FF)
    val onSurface = Color(0xFF1C1B1F)
    val surfaceVariant = Color(0xFFEFEEF7)
    val onSurfaceVariant = Color(0xFF49454E)
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val lightColorScheme = lightColorScheme(
        primary = AppColors.primary,
        onPrimary = AppColors.onPrimary,
        primaryContainer = AppColors.primaryContainer,
        onPrimaryContainer = AppColors.onPrimaryContainer,
        secondary = AppColors.secondary,
        onSecondary = AppColors.onSecondary,
        secondaryContainer = AppColors.secondaryContainer,
        onSecondaryContainer = AppColors.onSecondaryContainer,
        tertiary = AppColors.tertiary,
        onTertiary = AppColors.onTertiary,
        tertiaryContainer = AppColors.tertiaryContainer,
        onTertiaryContainer = AppColors.onTertiaryContainer,
        error = AppColors.error,
        onError = AppColors.onError,
        errorContainer = AppColors.errorContainer,
        onErrorContainer = AppColors.onErrorContainer,
        background = AppColors.background,
        onBackground = AppColors.onBackground,
        surface = AppColors.surface,
        onSurface = AppColors.onSurface,
        surfaceVariant = AppColors.surfaceVariant,
        onSurfaceVariant = AppColors.onSurfaceVariant
    )

    MaterialTheme(
        colorScheme = lightColorScheme,
        content = content
    )
}
