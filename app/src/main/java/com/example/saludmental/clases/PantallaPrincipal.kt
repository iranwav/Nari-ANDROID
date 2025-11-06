package com.example.saludmental.clases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun PantallaPrincipal(alNavegar: (Pantalla) -> Unit) {
    val frases = remember { BancoFrasesMusicales.frases }
    var indiceFraseActual by remember { mutableStateOf(frases.indices.random()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60000)
            indiceFraseActual = frases.indices.random()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Para ti",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Text(
            text = "Miles de recursos que te ayudarán a\nsentirte mejor",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TarjetaRecurso(
                icono = Icons.Default.WbSunny,
                etiqueta = "Inicio del Día",
                modifier = Modifier.weight(1f),
                onClick = { alNavegar(Pantalla.InicioDia) }
            )
            TarjetaRecurso(
                icono = Icons.Default.Bolt,
                etiqueta = "Motivación",
                modifier = Modifier.weight(1f),
                onClick = { alNavegar(Pantalla.Motivacion) }
            )
            TarjetaRecurso(
                icono = Icons.Default.SelfImprovement,
                etiqueta = "Relajación",
                modifier = Modifier.weight(1f),
                onClick = { alNavegar(Pantalla.Relajacion) }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TarjetaRecurso(
                icono = Icons.Default.EmojiEmotions,
                etiqueta = "Consejos",
                modifier = Modifier.weight(1f),
                onClick = { alNavegar(Pantalla.Consejos) }
            )
            TarjetaRecurso(
                icono = Icons.Default.Favorite,
                etiqueta = "Favoritos",
                modifier = Modifier.weight(1f),
                onClick = { alNavegar(Pantalla.Favoritos) }
            )
            TarjetaRecurso(
                icono = Icons.Default.Settings,
                etiqueta = "Configuración",
                modifier = Modifier.weight(1f),
                onClick = { alNavegar(Pantalla.Configuracion) }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Contenidos destacados",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                TarjetaMusicalDestacada(
                    icono = Icons.Default.MusicNote,
                    frase = frases[indiceFraseActual],
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                    onClick = { alNavegar(Pantalla.Musica) }
                )
            }
            item {
                TarjetaDestacada(
                    icono = Icons.Default.SmartToy,
                    titulo = "Nari IA",
                    descripcion = "¿Cómo te sientes?",
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                    onClick = { alNavegar(Pantalla.IA) }
                )
            }
            item {
                TarjetaDestacada(
                    icono = Icons.Default.MenuBook,
                    titulo = "Mi Blog Personal",
                    descripcion = "Escribe tus pensamientos",
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    onClick = { alNavegar(Pantalla.Blog) }
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}