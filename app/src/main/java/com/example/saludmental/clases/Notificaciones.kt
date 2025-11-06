package com.example.saludmental.clases

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReceptorNotificaciones : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ReceptorNotif", "📢 Notificación recibida!")

        val todosLosConsejos = mutableListOf<String>().apply {
            addAll(BancoConsejos.inicioDia.map { it.texto })
            addAll(BancoConsejos.motivacion.map { it.texto })
            addAll(BancoConsejos.relajacion.map { it.texto })
        }

        val consejoAleatorio = todosLosConsejos.random()
        mostrarNotificacion(context, consejoAleatorio)
    }
}

fun mostrarNotificacion(context: Context, mensaje: String) {
    Log.d("Notificacion", "📱 Intentando mostrar notificación")

    crearCanalNotificaciones(context)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("Notificacion", "❌ Sin permiso de notificaciones")
            return
        }
    }

    val notificacion = NotificationCompat.Builder(context, "frases_diarias")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Nari - Frase del momento")
        .setContentText(mensaje)
        .setStyle(NotificationCompat.BigTextStyle().bigText(mensaje))
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    try {
        NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notificacion)
        Log.d("Notificacion", "✅ Notificación enviada exitosamente")
    } catch (e: SecurityException) {
        Log.e("Notificacion", "❌ Error de seguridad: ${e.message}")
    }
}

fun crearCanalNotificaciones(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val nombre = "Frases Motivacionales"
        val descripcion = "Notificaciones con frases para tu bienestar"
        val importancia = NotificationManager.IMPORTANCE_HIGH

        val canal = NotificationChannel("frases_diarias", nombre, importancia).apply {
            description = descripcion
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(canal)
        Log.d("Canal", "✅ Canal de notificaciones creado")
    }
}

fun programarNotificaciones(context: Context, activar: Boolean) {
    Log.d("Programar", "🔔 Programando notificaciones: $activar")

    crearCanalNotificaciones(context)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    if (alarmManager == null) {
        Log.e("Programar", "❌ No se pudo obtener AlarmManager")
        return
    }

    val intent = Intent(context, ReceptorNotificaciones::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    if (activar) {
        try {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 30_000,
                30_000,
                pendingIntent
            )
            Log.d("Programar", "✅ Alarma programada cada 30 segundos")
        } catch (e: SecurityException) {
            Log.e("Programar", "❌ Error al programar alarma: ${e.message}")
        }
    } else {
        alarmManager.cancel(pendingIntent)
        Log.d("Programar", "🛑 Alarmas canceladas")
    }
}

fun programarNotificacionesProduccion(context: Context, activar: Boolean) {
    crearCanalNotificaciones(context)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
    val intent = Intent(context, ReceptorNotificaciones::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    if (activar) {
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + AlarmManager.INTERVAL_HOUR,
            AlarmManager.INTERVAL_HOUR,
            pendingIntent
        )
    } else {
        alarmManager.cancel(pendingIntent)
    }
}