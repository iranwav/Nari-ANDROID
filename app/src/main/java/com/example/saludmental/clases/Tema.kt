package com.example.saludmental.clases

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TemaSaludMental(
    modoOscuro: Boolean = isSystemInDarkTheme(),
    colorPrincipal: Long = 0xFF6B5B95,
    contenido: @Composable () -> Unit
) {
    val esquemaColores = if (modoOscuro) {
        darkColorScheme(
            primary = Color(colorPrincipal),
            secondary = Color(colorPrincipal).copy(alpha = 0.7f),
            background = Color(0xFF121212),
            surface = Color(0xFF1E1E1E),
            onBackground = Color(0xFFE1E1E1),
            onSurface = Color(0xFFE1E1E1)
        )
    } else {
        lightColorScheme(
            primary = Color(colorPrincipal),
            secondary = Color(colorPrincipal).copy(alpha = 0.7f),
            background = Color(0xFFF5F5F5),
            surface = Color.White,
            onBackground = Color(0xFF1C1B1F),
            onSurface = Color(0xFF1C1B1F)
        )
    }

    MaterialTheme(
        colorScheme = esquemaColores,
        content = contenido
    )
}

object ColoresDisponibles {
    val colores = listOf(
        0xFF6B5B95 to "Púrpura",
        0xFFE91E63 to "Rosa",
        0xFF2196F3 to "Azul",
        0xFF4CAF50 to "Verde",
        0xFFFF9800 to "Naranja",
        0xFF9C27B0 to "Morado"
    )
}