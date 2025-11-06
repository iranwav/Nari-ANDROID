package com.example.saludmental

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.BackHandler
import androidx.core.content.ContextCompat
import com.example.saludmental.clases.*

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            programarNotificaciones(this, activar = true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        solicitarPermisoNotificaciones()

        setContent {
            AppPrincipal()
        }
    }

    private fun solicitarPermisoNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    programarNotificaciones(this, activar = true)
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            programarNotificaciones(this, activar = true)
        }
    }
}

@Composable
fun AppPrincipal() {
    val context = LocalContext.current
    var pantallaActual by remember { mutableStateOf<Pantalla>(Pantalla.Principal) }

    val preferencias = remember { cargarPreferenciasTema(context) }
    var modoOscuro by remember { mutableStateOf(preferencias.modoOscuro) }
    var colorPrincipal by remember { mutableStateOf(preferencias.colorPrincipal) }

    BackHandler(enabled = pantallaActual != Pantalla.Principal) {
        pantallaActual = Pantalla.Principal
    }

    TemaSaludMental(
        modoOscuro = modoOscuro,
        colorPrincipal = colorPrincipal
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            when (pantallaActual) {
                Pantalla.Principal -> PantallaPrincipal(
                    alNavegar = { pantalla -> pantallaActual = pantalla }
                )

                Pantalla.Blog -> PantallaBlog(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.Musica -> PantallaMusica(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.InicioDia -> PantallaInicioDia(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.Motivacion -> PantallaMotivacion(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.Relajacion -> PantallaRelajacion(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.Consejos -> PantallaConsejos(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.Favoritos -> PantallaFavoritos(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )

                Pantalla.Configuracion -> PantallaConfiguracion(
                    alVolver = { pantallaActual = Pantalla.Principal },
                    modoOscuroActual = modoOscuro,
                    colorActual = colorPrincipal,
                    alCambiarModoOscuro = { nuevoModo ->
                        modoOscuro = nuevoModo
                        guardarPreferenciasTema(context, nuevoModo, colorPrincipal)
                    },
                    alCambiarColor = { nuevoColor ->
                        colorPrincipal = nuevoColor
                        guardarPreferenciasTema(context, modoOscuro, nuevoColor)
                    }
                )

                Pantalla.IA -> PantallaIA(
                    alVolver = { pantallaActual = Pantalla.Principal }
                )
            }
        }
    }
}