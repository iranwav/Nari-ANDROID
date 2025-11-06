package com.example.saludmental.clases

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class PlaylistData(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val esPredeterminada: Boolean = false
)


private const val PREFS_NAME = "playlists_prefs"
private const val KEY_PLAYLISTS = "playlists_guardadas"

fun guardarPlaylists(context: Context, playlists: List<PlaylistData>) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val json = Gson().toJson(playlists)
    prefs.edit().putString(KEY_PLAYLISTS, json).apply()
}

fun cargarPlaylists(context: Context): List<PlaylistData> {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val json = prefs.getString(KEY_PLAYLISTS, null) ?: return emptyList()
    val type = object : TypeToken<List<PlaylistData>>() {}.type
    return Gson().fromJson(json, type)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMusica(alVolver: () -> Unit) {
    val context = LocalContext.current

    var playlistsPersonalizadas by remember { mutableStateOf(cargarPlaylists(context)) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    LaunchedEffect(playlistsPersonalizadas) {
        guardarPlaylists(context, playlistsPersonalizadas)
    }

    val todasLasPlaylists = remember(playlistsPersonalizadas) {
        BancoPlaylists.playlists.map {
            PlaylistData(it.id, it.nombre, it.descripcion, esPredeterminada = true)
        } + playlistsPersonalizadas
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modo Música") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { mostrarDialogo = true }) {
                        Icon(Icons.Default.Add, "Agregar Playlist", tint = Color.White)
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
                .padding(16.dp)
        ) {
            Text(
                "Escucha playlists personalizadas en inglés y español",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            todasLasPlaylists.forEach { playlist ->
                TarjetaPlaylistPersonalizada(
                    playlist = playlist,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://open.spotify.com/playlist/${playlist.id}")
                            setPackage("com.spotify.music")
                        }
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            val webIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://open.spotify.com/playlist/${playlist.id}")
                            )
                            context.startActivity(webIntent)
                        }
                    },
                    onDelete = if (!playlist.esPredeterminada) {
                        {
                            playlistsPersonalizadas =
                                playlistsPersonalizadas.filter { it.id != playlist.id }
                        }
                    } else null
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Tip: Escuchar música relajante 20 minutos al día reduce el estrés.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }

    if (mostrarDialogo) {
        DialogoAgregarPlaylist(
            onDismiss = { mostrarDialogo = false },
            onConfirm = { nombre, descripcion, link ->
                val playlistId = extraerIdDeSpotify(link)
                if (playlistId != null) {
                    val nuevaPlaylist = PlaylistData(
                        id = playlistId,
                        nombre = nombre,
                        descripcion = descripcion,
                        esPredeterminada = false
                    )
                    playlistsPersonalizadas = playlistsPersonalizadas + nuevaPlaylist
                    mostrarDialogo = false
                }
            }
        )
    }
}


@Composable
fun TarjetaPlaylistPersonalizada(
    playlist: PlaylistData,
    onClick: () -> Unit,
    onDelete: (() -> Unit)?
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (playlist.esPredeterminada)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                if (playlist.esPredeterminada) Icons.Default.Star else Icons.Default.MusicNote,
                contentDescription = null,
                tint = if (playlist.esPredeterminada)
                    Color(0xFFFFD700)
                else
                    MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = playlist.nombre,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (playlist.esPredeterminada)
                            MaterialTheme.colorScheme.onPrimaryContainer
                        else
                            MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    if (playlist.esPredeterminada) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0xFFFFD700).copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "Original",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFD700),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = playlist.descripcion,
                    fontSize = 13.sp,
                    color = if (playlist.esPredeterminada)
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    else
                        MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                )
            }

            if (onDelete != null) {
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Composable
fun DialogoAgregarPlaylist(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var errorLink by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Agregar Nueva Playlist",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre de la playlist") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.MusicNote, contentDescription = null) }
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 3,
                    leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
                )

                OutlinedTextField(
                    value = link,
                    onValueChange = {
                        link = it
                        errorLink = false
                    },
                    label = { Text("Link de Spotify") },
                    placeholder = { Text("https://open.spotify.com/playlist/...") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = errorLink,
                    leadingIcon = { Icon(Icons.Default.Link, contentDescription = null) },
                    supportingText = {
                        if (errorLink) {
                            Text(
                                text = "Link inválido. Usa un link de playlist de Spotify",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (nombre.isBlank() || descripcion.isBlank() || link.isBlank()) return@Button
                            val playlistId = extraerIdDeSpotify(link)
                            if (playlistId != null) {
                                onConfirm(nombre, descripcion, link)
                            } else {
                                errorLink = true
                            }
                        },
                        enabled = nombre.isNotBlank() && descripcion.isNotBlank() && link.isNotBlank()
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Agregar")
                    }
                }
            }
        }
    }
}


fun extraerIdDeSpotify(link: String): String? {
    val regex = Regex("playlist/([a-zA-Z0-9]+)")
    val match = regex.find(link)
    return match?.groupValues?.getOrNull(1)
}
