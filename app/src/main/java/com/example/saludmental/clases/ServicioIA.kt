package com.example.saludmental.clases

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

data class GeminiRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig = GenerationConfig()
)

data class Content(
    val parts: List<Part>
)

data class Part(
    val text: String
)

data class GenerationConfig(
    val temperature: Double = 0.7,
    val topK: Int = 40,
    val topP: Double = 0.95,
    val maxOutputTokens: Int = 1024
)

data class GeminiResponse(
    val candidates: List<Candidate>?,
    val error: ErrorResponse?
)

data class Candidate(
    val content: Content?,
    @SerializedName("finishReason") val finishReason: String?
)

data class ErrorResponse(
    val code: Int?,
    val message: String?,
    val status: String?
)

object ServicioIA {
    private const val TAG = "ServicioIA"

    private const val API_KEY = "AIzaSyDwD5bFo-aYlDl5z323jo_NDR2IUjADyls"

    private const val API_BASE_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    private const val SISTEMA_PROMPT = """
Eres un asistente de apoyo emocional y bienestar mental, amable, empático y profesional.
Tu nombre es y siempre será Nari.

INSTRUCCIONES:
- Si es que te preguntan tu nombre o que eres di que eres Nari, el asistente de apoyo emocional
- Responde con calidez y empatía, como un psicólogo comprensivo
- Usa un lenguaje sencillo y cercano en español
- Haz preguntas reflexivas cuando sea apropiado
- Ofrece técnicas prácticas de manejo emocional cuando sea relevante
- IMPORTANTE: Siempre menciona que no reemplazas la ayuda profesional en casos graves
- Mantén las respuestas concisas (máximo 3-4 párrafos)
- Sé positivo pero realista
- Valida las emociones del usuario
- Siempre mantente en tu papel de psicologo, si te preguntan de alguna otra area que no sea psicologia dile que eres un asistente de apoyo emocional
- Solo usa respuestas largas si son necesarias, si es algo sencillo se breve
- Siempre responde con coherencia a lo que te hayan dicho, no hagas que se confunda el usuario
NUNCA:
- Des diagnósticos médicos
- Prescribas medicamentos
- Prometas soluciones mágicas
- Juzgues o invalides los sentimientos del usuario
"""

    suspend fun enviarMensaje(mensajeUsuario: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val apiUrl = "$API_BASE_URL?key=$API_KEY"

            Log.d(TAG, "Enviando mensaje a Gemini API")
            Log.d(TAG, "API Key configurada: ${API_KEY.isNotBlank()}")
            Log.d(TAG, "API Key length: ${API_KEY.length}")

            val promptCompleto = "$SISTEMA_PROMPT\n\nUsuario: $mensajeUsuario\n\nAsistente:"

            val request = GeminiRequest(
                contents = listOf(
                    Content(parts = listOf(Part(promptCompleto)))
                )
            )

            val jsonBody = gson.toJson(request)
            val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

            val httpRequest = Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()

            Log.d(TAG, "Realizando petición HTTP...")
            val response = client.newCall(httpRequest).execute()
            val responseBody = response.body?.string()

            Log.d(TAG, "Código de respuesta: ${response.code}")

            if (!response.isSuccessful) {
                Log.e(TAG, "Error HTTP ${response.code}")
                Log.e(TAG, "Response body: $responseBody")

                val errorMsg = when (response.code) {
                    400 -> "Error 400: Solicitud incorrecta. Verifica el formato."
                    401 -> "Error 401: API Key inválida. Verifica que sea correcta."
                    403 -> "Error 403: Sin acceso. Habilita la API de Gemini en Google Cloud Console."
                    404 -> "Error 404: Endpoint no encontrado. Verifica la URL de la API."
                    429 -> "Error 429: Demasiadas solicitudes. Espera un momento."
                    500, 503 -> "Error del servidor de Gemini. Intenta más tarde."
                    else -> "Error ${response.code}: ${responseBody?.take(200)}"
                }
                return@withContext Result.failure(Exception(errorMsg))
            }

            if (responseBody == null || responseBody.isBlank()) {
                Log.e(TAG, "Respuesta vacía del servidor")
                return@withContext Result.failure(Exception("Respuesta vacía del servidor"))
            }

            Log.d(TAG, "Parseando respuesta JSON...")
            val geminiResponse = try {
                gson.fromJson(responseBody, GeminiResponse::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "Error al parsear JSON", e)
                return@withContext Result.failure(Exception("Error procesando respuesta: ${e.message}"))
            }

            if (geminiResponse.error != null) {
                Log.e(TAG, "Error de Gemini: ${geminiResponse.error.message}")
                return@withContext Result.failure(
                    Exception("Error de Gemini: ${geminiResponse.error.message}")
                )
            }

            val respuestaIA = geminiResponse.candidates?.firstOrNull()
                ?.content?.parts?.firstOrNull()?.text

            if (respuestaIA.isNullOrBlank()) {
                Log.e(TAG, "La IA no generó respuesta")
                return@withContext Result.failure(Exception("La IA no generó respuesta"))
            }

            Log.d(TAG, "Respuesta exitosa (${respuestaIA.length} caracteres)")
            Result.success(respuestaIA.trim())

        } catch (e: java.net.UnknownHostException) {
            Log.e(TAG, "Sin conexión a internet", e)
            Result.failure(Exception("Sin conexión a internet. Verifica tu WiFi o datos móviles."))
        } catch (e: java.net.SocketTimeoutException) {
            Log.e(TAG, "Timeout en la conexión", e)
            Result.failure(Exception("Tiempo de espera agotado. Intenta de nuevo."))
        } catch (e: Exception) {
            Log.e(TAG, "Error general", e)
            Result.failure(Exception("Error: ${e.message}"))
        }
    }

    fun obtenerMensajeInicial(): String {
        return "Hola, mi nombre es Nari, soy tu asistente de bienestar emocional. Estoy aquí para escucharte y apoyarte. ¿Cómo te sientes hoy?"
    }
}