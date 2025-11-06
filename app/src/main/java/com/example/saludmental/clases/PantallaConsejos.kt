package com.example.saludmental.clases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConsejos(alVolver: () -> Unit) {
    var estadoSeleccionado by remember { mutableStateOf<String?>(null) }

    if (estadoSeleccionado == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("¿Cómo te sientes?") },
                    navigationIcon = {
                        IconButton(onClick = alVolver) {
                            Icon(Icons.Default.ArrowBack, "Volver")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Selecciona tu estado de ánimo:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                TarjetaEstadoAnimo("😊 Feliz", Color(0xFFFFEB3B)) { estadoSeleccionado = "Feliz" }
                TarjetaEstadoAnimo("😢 Triste", Color(0xFF90CAF9)) { estadoSeleccionado = "Triste" }
                TarjetaEstadoAnimo("😰 Ansioso", Color(0xFFFFAB91)) { estadoSeleccionado = "Ansioso" }
                TarjetaEstadoAnimo("😠 Enojado", Color(0xFFEF5350)) { estadoSeleccionado = "Enojado" }
                TarjetaEstadoAnimo("😴 Cansado", Color(0xFFB39DDB)) { estadoSeleccionado = "Cansado" }
                TarjetaEstadoAnimo("😐 Neutral", Color(0xFFBDBDBD)) { estadoSeleccionado = "Neutral" }
            }
        }
    } else {
        PantallaConsejoEstado(estado = estadoSeleccionado!!, alVolver = { estadoSeleccionado = null })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConsejoEstado(estado: String, alVolver: () -> Unit) {
    val context = LocalContext.current
    var favoritos by remember { mutableStateOf(cargarFavoritos(context)) }

    val consejos = remember(estado) { BancoConsejos.obtenerConsejosPorEstado(estado) }
    var indiceConsejoActual by remember { mutableStateOf(0) }

    LayoutPantallaConsejo(
        titulo = "Consejo para: $estado",
        consejo = consejos[indiceConsejoActual],
        alVolver = alVolver,
        alAnterior = {
            indiceConsejoActual = if (indiceConsejoActual - 1 < 0)
                consejos.size - 1
            else
                indiceConsejoActual - 1
        },
        alSiguiente = { indiceConsejoActual = (indiceConsejoActual + 1) % consejos.size },
        alCompartir = { compartirConsejo(context, consejos[indiceConsejoActual]) },
        alFavorito = {
            if (favoritos.none { it.id == consejos[indiceConsejoActual].id }) {
                favoritos = favoritos + consejos[indiceConsejoActual]
                guardarFavoritos(context, favoritos)
            }
        },
        esFavorito = favoritos.any { it.id == consejos[indiceConsejoActual].id }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicioDia(alVolver: () -> Unit) {
    val context = LocalContext.current
    var favoritos by remember { mutableStateOf(cargarFavoritos(context)) }
    val consejos = remember { BancoConsejos.inicioDia }
    var indiceConsejoActual by remember { mutableStateOf(0) }

    LayoutPantallaConsejo(
        titulo = "Inicio del día",
        consejo = consejos[indiceConsejoActual],
        alVolver = alVolver,
        alAnterior = {
            indiceConsejoActual = if (indiceConsejoActual - 1 < 0)
                consejos.size - 1
            else
                indiceConsejoActual - 1
        },
        alSiguiente = { indiceConsejoActual = (indiceConsejoActual + 1) % consejos.size },
        alCompartir = { compartirConsejo(context, consejos[indiceConsejoActual]) },
        alFavorito = {
            if (favoritos.none { it.id == consejos[indiceConsejoActual].id }) {
                favoritos = favoritos + consejos[indiceConsejoActual]
                guardarFavoritos(context, favoritos)
            }
        },
        esFavorito = favoritos.any { it.id == consejos[indiceConsejoActual].id }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMotivacion(alVolver: () -> Unit) {
    val context = LocalContext.current
    var favoritos by remember { mutableStateOf(cargarFavoritos(context)) }
    val consejos = remember { BancoConsejos.motivacion }
    var indiceConsejoActual by remember { mutableStateOf(0) }

    LayoutPantallaConsejo(
        titulo = "Motivación y energía",
        consejo = consejos[indiceConsejoActual],
        alVolver = alVolver,
        alAnterior = {
            indiceConsejoActual = if (indiceConsejoActual - 1 < 0)
                consejos.size - 1
            else
                indiceConsejoActual - 1
        },
        alSiguiente = { indiceConsejoActual = (indiceConsejoActual + 1) % consejos.size },
        alCompartir = { compartirConsejo(context, consejos[indiceConsejoActual]) },
        alFavorito = {
            if (favoritos.none { it.id == consejos[indiceConsejoActual].id }) {
                favoritos = favoritos + consejos[indiceConsejoActual]
                guardarFavoritos(context, favoritos)
            }
        },
        esFavorito = favoritos.any { it.id == consejos[indiceConsejoActual].id }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRelajacion(alVolver: () -> Unit) {
    val context = LocalContext.current
    var favoritos by remember { mutableStateOf(cargarFavoritos(context)) }
    val consejos = remember { BancoConsejos.relajacion }
    var indiceConsejoActual by remember { mutableStateOf(0) }

    LayoutPantallaConsejo(
        titulo = "Relajación y descanso",
        consejo = consejos[indiceConsejoActual],
        alVolver = alVolver,
        alAnterior = {
            indiceConsejoActual = if (indiceConsejoActual - 1 < 0)
                consejos.size - 1
            else
                indiceConsejoActual - 1
        },
        alSiguiente = { indiceConsejoActual = (indiceConsejoActual + 1) % consejos.size },
        alCompartir = { compartirConsejo(context, consejos[indiceConsejoActual]) },
        alFavorito = {
            if (favoritos.none { it.id == consejos[indiceConsejoActual].id }) {
                favoritos = favoritos + consejos[indiceConsejoActual]
                guardarFavoritos(context, favoritos)
            }
        },
        esFavorito = favoritos.any { it.id == consejos[indiceConsejoActual].id }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutPantallaConsejo(
    titulo: String,
    consejo: Consejo,
    alVolver: () -> Unit,
    alAnterior: () -> Unit,
    alSiguiente: () -> Unit,
    alCompartir: () -> Unit,
    alFavorito: () -> Unit,
    esFavorito: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(titulo) },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.FormatQuote,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = consejo.texto,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = alAnterior,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.ArrowBack, "Anterior")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Anterior")
                }

                Button(
                    onClick = alSiguiente,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.ArrowForward, "Siguiente")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Siguiente")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = alCompartir,
                    modifier = Modifier
                        .size(56.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                ) {
                    Icon(Icons.Default.Share, "Compartir", tint = Color.White)
                }

                Spacer(modifier = Modifier.width(12.dp))

                IconButton(
                    onClick = alFavorito,
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            if (esFavorito) Color(0xFFE91E63) else MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(12.dp)
                        )
                ) {
                    Icon(
                        if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        "Favorito",
                        tint = Color.White
                    )
                }
            }
        }
    }
}