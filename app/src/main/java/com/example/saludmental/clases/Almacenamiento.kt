package com.example.saludmental.clases

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun guardarFavoritos(context: Context, favoritos: List<Consejo>) {
    val prefs = context.getSharedPreferences("favoritos_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = gson.toJson(favoritos)
    prefs.edit().putString("favoritos", json).apply()
}

fun cargarFavoritos(context: Context): List<Consejo> {
    val prefs = context.getSharedPreferences("favoritos_prefs", Context.MODE_PRIVATE)
    val json = prefs.getString("favoritos", null) ?: return emptyList()
    val gson = Gson()
    val type = object : TypeToken<List<Consejo>>() {}.type
    return gson.fromJson(json, type)
}

fun guardarNotas(context: Context, notas: List<NotaBlog>) {
    val prefs = context.getSharedPreferences("blog_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = gson.toJson(notas)
    prefs.edit().putString("notas", json).apply()
}

fun cargarNotas(context: Context): List<NotaBlog> {
    val prefs = context.getSharedPreferences("blog_prefs", Context.MODE_PRIVATE)
    val json = prefs.getString("notas", null) ?: return emptyList()
    val gson = Gson()
    val type = object : TypeToken<List<NotaBlog>>() {}.type
    return gson.fromJson(json, type)
}

fun guardarPreferenciasTema(context: Context, modoOscuro: Boolean, colorPrincipal: Long) {
    val prefs = context.getSharedPreferences("tema_prefs", Context.MODE_PRIVATE)
    prefs.edit()
        .putBoolean("modo_oscuro", modoOscuro)
        .putLong("color_principal", colorPrincipal)
        .apply()
}

fun cargarPreferenciasTema(context: Context): PreferenciasTema {
    val prefs = context.getSharedPreferences("tema_prefs", Context.MODE_PRIVATE)
    return PreferenciasTema(
        modoOscuro = prefs.getBoolean("modo_oscuro", false),
        colorPrincipal = prefs.getLong("color_principal", 0xFF6B5B95)
    )
}