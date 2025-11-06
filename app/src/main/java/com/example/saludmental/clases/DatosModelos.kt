package com.example.saludmental.clases

import java.util.*

data class Consejo(
    val id: String = UUID.randomUUID().toString(),
    val texto: String,
    val categoria: String
)

data class NotaBlog(
    val id: String = UUID.randomUUID().toString(),
    val titulo: String,
    val contenido: String,
    val fecha: Long = System.currentTimeMillis()
)

data class FraseMusical(
    val nombreCancion: String,
    val frase: String,
    val artista: String
)

data class PlaylistSpotify(
    val nombre: String,
    val descripcion: String,
    val id: String
)

data class PreferenciasTema(
    val modoOscuro: Boolean = false,
    val colorPrincipal: Long = 0xFF6B5B95
)

sealed class Pantalla {
    object Principal : Pantalla()
    object Blog : Pantalla()
    object Musica : Pantalla()
    object InicioDia : Pantalla()
    object Motivacion : Pantalla()
    object Relajacion : Pantalla()
    object Consejos : Pantalla()
    object Favoritos : Pantalla()
    object Configuracion : Pantalla()
    object IA : Pantalla()
}