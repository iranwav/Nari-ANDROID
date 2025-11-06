package com.example.saludmental.clases

import android.content.Context
import android.content.Intent

fun compartirConsejo(context: Context, consejo: Consejo) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "${consejo.texto}\n\n- SaludMental App")
    }
    context.startActivity(Intent.createChooser(intent, "Compartir frase"))
}


object BancoConsejos {
    val inicioDia = listOf(
        Consejo(texto = "Buenos días! Hoy es un nuevo comienzo lleno de posibilidades", categoria = "Inicio del día"),
        Consejo(texto = "Comienza tu día con gratitud: piensa en tres cosas por las que estás agradecido", categoria = "Inicio del día"),
        Consejo(texto = "Despierta con energía positiva y propósito", categoria = "Inicio del día"),
        Consejo(texto = "Cada mañana es una página en blanco para escribir tu historia", categoria = "Inicio del día"),
        Consejo(texto = "Respira profundo y sonríe, un nuevo día te espera", categoria = "Inicio del día"),
        Consejo(texto = "Establece una intención clara para las próximas 24 horas", categoria = "Inicio del día"),
        Consejo(texto = "Bebe un vaso de agua al despertar para hidratarte", categoria = "Inicio del día"),
        Consejo(texto = "Evita revisar el móvil inmediatamente al despertar", categoria = "Inicio del día"),
        Consejo(texto = "Dedica 5 minutos a estiramientos suaves", categoria = "Inicio del día"),
        Consejo(texto = "Escucha una canción que te ponga de buen humor", categoria = "Inicio del día"),
        Consejo(texto = "Visualiza un día exitoso y lleno de paz", categoria = "Inicio del día"),
        Consejo(texto = "Organiza tu espacio de trabajo antes de empezar", categoria = "Inicio del día"),
        Consejo(texto = "Haz una lista de 3 tareas importantes (no más)", categoria = "Inicio del día"),
        Consejo(texto = "Desayuna algo nutritivo que te dé energía", categoria = "Inicio del día"),
        Consejo(texto = "Sal a tomar un poco de sol o aire fresco", categoria = "Inicio del día"),
        Consejo(texto = "Recuerda que tienes el poder de elegir tu actitud", categoria = "Inicio del día"),
        Consejo(texto = "No te apresures, tómate tu tiempo para prepararte", categoria = "Inicio del día"),
        Consejo(texto = "Acepta que no todo saldrá perfecto y está bien", categoria = "Inicio del día"),
        Consejo(texto = "Sé amable contigo mismo hoy", categoria = "Inicio del día"),
        Consejo(texto = "Regálate un momento de silencio y meditación", categoria = "Inicio del día"),
        Consejo(texto = "Recuerda tu 'por qué' o el propósito que te mueve", categoria = "Inicio del día"),
        Consejo(texto = "Agradece la cama que te dio descanso", categoria = "Inicio del día"),
        Consejo(texto = "Define un pequeño objetivo alcanzable para el mediodía", categoria = "Inicio del día"),
        Consejo(texto = "Sonríe al espejo, el día te devolverá la sonrisa", categoria = "Inicio del día"),
        Consejo(texto = "Hoy es una oportunidad para aprender y crecer", categoria = "Inicio del día")
    )

    val motivacion = listOf(
        Consejo(texto = "Tú eres más fuerte de lo que piensas", categoria = "Motivación"),
        Consejo(texto = "El éxito es la suma de pequeños esfuerzos repetidos día tras día", categoria = "Motivación"),
        Consejo(texto = "No te rindas, lo mejor está por venir", categoria = "Motivación"),
        Consejo(texto = "Cree en ti mismo y todo será posible", categoria = "Motivación"),
        Consejo(texto = "Tu única limitación eres tú mismo", categoria = "Motivación"),
        Consejo(texto = "Si tienes miedo, hazlo con miedo", categoria = "Motivación"),
        Consejo(texto = "La disciplina es el puente entre metas y logros", categoria = "Motivación"),
        Consejo(texto = "Convierte los 'errores' en lecciones valiosas", categoria = "Motivación"),
        Consejo(texto = "Pequeños avances son mejores que ninguna acción", categoria = "Motivación"),
        Consejo(texto = "Rodéate de personas que crean en tu potencial", categoria = "Motivación"),
        Consejo(texto = "Define tu siguiente paso, no tienes que ver todo el camino", categoria = "Motivación"),
        Consejo(texto = "La persistencia vence a la resistencia", categoria = "Motivación"),
        Consejo(texto = "Sé tu propia fuente de inspiración", categoria = "Motivación"),
        Consejo(texto = "El momento perfecto es ahora", categoria = "Motivación"),
        Consejo(texto = "Recuerda por qué empezaste", categoria = "Motivación"),
        Consejo(texto = "Celebra tus pequeñas victorias diarias", categoria = "Motivación"),
        Consejo(texto = "No busques ser perfecto, busca ser mejor", categoria = "Motivación"),
        Consejo(texto = "El dolor que sientes hoy será la fuerza que sientas mañana", categoria = "Motivación"),
        Consejo(texto = "Transforma la envidia en admiración y acción", categoria = "Motivación"),
        Consejo(texto = "Haz algo hoy que tu yo futuro te agradecerá", categoria = "Motivación"),
        Consejo(texto = "Tu potencial es ilimitado", categoria = "Motivación"),
        Consejo(texto = "Mantén la vista en las estrellas y los pies en la tierra", categoria = "Motivación"),
        Consejo(texto = "Si no te desafía, no te cambia", categoria = "Motivación"),
        Consejo(texto = "La mejor venganza es el éxito masivo", categoria = "Motivación"),
        Consejo(texto = "Cada fracaso te acerca más al éxito", categoria = "Motivación")
    )

    val relajacion = listOf(
        Consejo(texto = "Respira profundo: inhala paz, exhala estrés", categoria = "Relajación"),
        Consejo(texto = "Está bien tomarte un descanso, lo mereces", categoria = "Relajación"),
        Consejo(texto = "En la calma encuentras tu verdadero poder", categoria = "Relajación"),
        Consejo(texto = "Cierra los ojos y permite que tu mente descanse", categoria = "Relajación"),
        Consejo(texto = "La tranquilidad es un tesoro que llevas dentro", categoria = "Relajación"),
        Consejo(texto = "Date un baño caliente para soltar la tensión", categoria = "Relajación"),
        Consejo(texto = "Practica la atención plena (mindfulness) por 2 minutos", categoria = "Relajación"),
        Consejo(texto = "Escucha sonidos de la naturaleza para calmarte", categoria = "Relajación"),
        Consejo(texto = "Disfruta de una taza de té sin distracciones", categoria = "Relajación"),
        Consejo(texto = "Baja el ritmo de tu voz y tus movimientos", categoria = "Relajación"),
        Consejo(texto = "Desconecta de las pantallas por un rato", categoria = "Relajación"),
        Consejo(texto = "Recuerda que no tienes que resolverlo todo ahora", categoria = "Relajación"),
        Consejo(texto = "Escribe tus preocupaciones en un papel y déjalas ahí", categoria = "Relajación"),
        Consejo(texto = "Aprecia las pequeñas cosas que te rodean", categoria = "Relajación"),
        Consejo(texto = "Estira tu cuerpo, especialmente cuello y hombros", categoria = "Relajación"),
        Consejo(texto = "La paz comienza en el momento en que eliges no permitir que otra persona o evento controle tus emociones", categoria = "Relajación"),
        Consejo(texto = "Pasa tiempo en silencio, sin música ni televisión", categoria = "Relajación"),
        Consejo(texto = "Permítete no hacer nada productivo por 15 minutos", categoria = "Relajación"),
        Consejo(texto = "Abraza a un ser querido o a una mascota", categoria = "Relajación"),
        Consejo(texto = "Ajusta la iluminación para que sea más tenue y acogedora", categoria = "Relajación"),
        Consejo(texto = "Pon límites a tus responsabilidades por hoy", categoria = "Relajación"),
        Consejo(texto = "Recuerda que la prisa es enemiga de la calma", categoria = "Relajación"),
        Consejo(texto = "Practica el desapego de lo que no puedes controlar", categoria = "Relajación"),
        Consejo(texto = "Observa tu respiración: es tu ancla al presente", categoria = "Relajación"),
        Consejo(texto = "Da un paseo corto sin un destino fijo", categoria = "Relajación")
    )

    fun obtenerConsejosPorEstado(estado: String): List<Consejo> {
        return when (estado) {
            "Feliz" -> listOf(
                Consejo(texto = "¡Disfruta este momento! La felicidad es un regalo", categoria = estado),
                Consejo(texto = "Comparte tu alegría con los demás", categoria = estado),
                Consejo(texto = "Agradece por este sentimiento positivo", categoria = estado),
                Consejo(texto = "Documenta este momento. Un diario de gratitud ayuda a fijar la emoción", categoria = estado),
                Consejo(texto = "Haz algo altruista hoy. Dar te hace aún más feliz", categoria = estado),
                Consejo(texto = "Llama a alguien que aprecies para compartir buenas noticias", categoria = estado),
                Consejo(texto = "Baila tu canción favorita para amplificar la energía positiva", categoria = estado),
                Consejo(texto = "Detente un momento y siente la felicidad en tu cuerpo", categoria = estado),
                Consejo(texto = "Busca el sol. La luz natural potencia tu ánimo", categoria = estado),
                Consejo(texto = "Sonríe a un extraño, puedes mejorar su día", categoria = estado),
                Consejo(texto = "Haz una lista de 5 razones por las que estás feliz AHORA", categoria = estado),
                Consejo(texto = "Planea algo divertido para más tarde o el fin de semana", categoria = estado),
                Consejo(texto = "Permítete un pequeño capricho o gusto especial", categoria = estado),
                Consejo(texto = "Reflexiona sobre qué te ha llevado a este estado de ánimo", categoria = estado),
                Consejo(texto = "Ayuda a alguien a encontrar un motivo para sonreír", categoria = estado),
                Consejo(texto = "Recuerda que la felicidad es un viaje, no un destino", categoria = estado),
                Consejo(texto = "Abraza a alguien (¡los abrazos liberan oxitocina!)", categoria = estado),
                Consejo(texto = "Visualiza el éxito en tu próximo proyecto", categoria = estado),
                Consejo(texto = "Haz un dibujo o garabato para expresar tu alegría", categoria = estado),
                Consejo(texto = "Toma una foto de lo que te hace feliz en este instante", categoria = estado),
                Consejo(texto = "Aprovecha esta energía para completar una tarea pendiente", categoria = estado),
                Consejo(texto = "No te preocupes por 'mantenerla', simplemente vívela", categoria = estado),
                Consejo(texto = "Busca una nueva perspectiva: todo se ve mejor desde la felicidad", categoria = estado)
            )
            "Triste" -> listOf(
                Consejo(texto = "Está bien sentirse triste, es parte de ser humano", categoria = estado),
                Consejo(texto = "Esta tormenta pasará, siempre sale el sol después", categoria = estado),
                Consejo(texto = "Habla con alguien de confianza sobre lo que sientes", categoria = estado),
                Consejo(texto = "Permítete llorar, es una forma natural de liberar el dolor", categoria = estado),
                Consejo(texto = "Recuerda una cosa que amabas hacer de niño", categoria = estado),
                Consejo(texto = "Busca una manta suave y date un consuelo físico", categoria = estado),
                Consejo(texto = "Evita tomar decisiones importantes mientras te sientes así", categoria = estado),
                Consejo(texto = "Escribe una carta (sin enviarla) expresando tu dolor", categoria = estado),
                Consejo(texto = "Recuerda que esta emoción es temporal", categoria = estado),
                Consejo(texto = "Ponte una película o serie reconfortante que ya hayas visto", categoria = estado),
                Consejo(texto = "Sal a caminar. El movimiento ayuda a cambiar la perspectiva", categoria = estado),
                Consejo(texto = "Pequeños pasos: sólo enfócate en lo que puedes hacer en la próxima hora", categoria = estado),
                Consejo(texto = "Prepara tu comida favorita, incluso si solo comes un bocado", categoria = estado),
                Consejo(texto = "No te aísles por completo. Un mensaje es suficiente por hoy", categoria = estado),
                Consejo(texto = "Pregúntate: ¿Qué necesito *realmente* en este momento?", categoria = estado),
                Consejo(texto = "La autocompasión es clave. Háblate con la amabilidad que usarías con un amigo", categoria = estado),
                Consejo(texto = "Escucha música que te permita procesar la emoción, no que la evite", categoria = estado),
                Consejo(texto = "Date permiso para no tener que 'arreglar' nada por hoy", categoria = estado),
                Consejo(texto = "A veces, un buen descanso nocturno es la mejor medicina", categoria = estado),
                Consejo(texto = "Evita compararte con los demás; cada proceso es único", categoria = estado),
                Consejo(texto = "Recuerda que eres valioso, incluso en tu tristeza", categoria = estado),
                Consejo(texto = "Bebe agua o una infusión caliente para calmarte", categoria = estado),
                Consejo(texto = "Busca el contacto con la naturaleza, aunque sea mirar por la ventana", categoria = estado)
            )
            "Ansioso" -> listOf(
                Consejo(texto = "Respira: inhala 4 segundos, mantén 4, exhala 4", categoria = estado),
                Consejo(texto = "Enfócate en el presente, no en el futuro incierto", categoria = estado),
                Consejo(texto = "Todo va a estar bien, confía en el proceso", categoria = estado),
                Consejo(texto = "Nombra 5 cosas que puedes ver, 4 que puedes tocar, 3 que puedes oír, 2 que puedes oler, 1 que puedes saborear (Técnica 5-4-3-2-1)", categoria = estado),
                Consejo(texto = "Mueve tu cuerpo. Haz saltos o estiramientos bruscos para liberar adrenalina", categoria = estado),
                Consejo(texto = "Sujeta un objeto frío (como un hielo) para desviar la atención", categoria = estado),
                Consejo(texto = "Recuerda que los pensamientos no son hechos", categoria = estado),
                Consejo(texto = "Cuestiona tus miedos: ¿Cuál es la probabilidad real de que suceda?", categoria = estado),
                Consejo(texto = "Evita la cafeína y los azúcares por un rato", categoria = estado),
                Consejo(texto = "Define una 'Hora de Preocupación' y pospón los pensamientos hasta entonces", categoria = estado),
                Consejo(texto = "Haz un escaneo corporal para localizar dónde guardas la tensión", categoria = estado),
                Consejo(texto = "Concéntrate en una tarea simple que requiera atención (p. ej., ordenar un cajón)", categoria = estado),
                Consejo(texto = "Pide ayuda o compañía si te sientes abrumado", categoria = estado),
                Consejo(texto = "Repite un mantra de calma, como 'Estoy a salvo' o 'Esto pasará'", categoria = estado),
                Consejo(texto = "Visualiza un lugar seguro y pacífico en tu mente", categoria = estado),
                Consejo(texto = "Haz garabatos o colorea para ocupar la mente de forma no verbal", categoria = estado),
                Consejo(texto = "Relaja intencionalmente tus hombros y mandíbula", categoria = estado),
                Consejo(texto = "La ansiedad es energía sin dirección; canalízala a una actividad física", categoria = estado),
                Consejo(texto = "Revisa tu lista de pendientes y elimina lo no urgente", categoria = estado),
                Consejo(texto = "Evita las noticias o redes sociales que alimenten tu preocupación", categoria = estado),
                Consejo(texto = "Baja el volumen de los pensamientos catastróficos, imagínalos como un susurro", categoria = estado),
                Consejo(texto = "Recuerda tus logros pasados; has superado desafíos antes", categoria = estado),
                Consejo(texto = "Toma nota de cuándo y dónde comenzó el pico de ansiedad", categoria = estado)
            )
            "Enojado" -> listOf(
                Consejo(texto = "Cuenta hasta 10 antes de reaccionar", categoria = estado),
                Consejo(texto = "El enojo es válido, pero no dejes que te controle", categoria = estado),
                Consejo(texto = "Sal a caminar o haz ejercicio para liberar tensión", categoria = estado),
                Consejo(texto = "Tómate un 'tiempo fuera' y sal de la situación o habitación", categoria = estado),
                Consejo(texto = "Escribe en un papel todo lo que te molesta y luego rómpelo", categoria = estado),
                Consejo(texto = "Bebe agua fría lentamente para regular tu temperatura interna", categoria = estado),
                Consejo(texto = "Habla sobre el enojo con un tercero neutral antes de confrontar a alguien", categoria = estado),
                Consejo(texto = "La meta no es no enojarse, sino responder en lugar de reaccionar", categoria = estado),
                Consejo(texto = "Masajea el área entre el pulgar y el índice de tu mano", categoria = estado),
                Consejo(texto = "Recuerda el costo del enojo: ¿vale la pena este sentimiento?", categoria = estado),
                Consejo(texto = "Acepta que no puedes controlar las acciones de otras personas", categoria = estado),
                Consejo(texto = "Cambia el foco: piensa en algo que te haga sentir agradecido", categoria = estado),
                Consejo(texto = "Si estás en una discusión, haz una pausa de 10 minutos y regresa", categoria = estado),
                Consejo(texto = "Usa almohadas para golpear o gritar en silencio", categoria = estado),
                Consejo(texto = "Busca el humor en la situación, si es posible, para desescalar", categoria = estado),
                Consejo(texto = "Revisa si estás interpretando erróneamente las intenciones de otros", categoria = estado),
                Consejo(texto = "Escucha música con auriculares, sube el volumen para bloquear el ruido mental", categoria = estado),
                Consejo(texto = "Cuando te sientas más tranquilo, busca la solución, no la culpa", categoria = estado),
                Consejo(texto = "El perdón no es para el otro, es para tu propia paz", categoria = estado),
                Consejo(texto = "Identifica la necesidad subyacente: ¿Te sientes irrespetado, ignorado, herido?", categoria = estado),
                Consejo(texto = "Concentra toda tu atención en la punta de tus pies", categoria = estado),
                Consejo(texto = "Respira por la nariz y exhala lentamente por la boca, haciendo un sonido de alivio", categoria = estado),
                Consejo(texto = "Recuerda que tu paz es más importante que tener la razón", categoria = estado)
            )
            "Cansado" -> listOf(
                Consejo(texto = "Descansa, tu cuerpo te lo está pidiendo", categoria = estado),
                Consejo(texto = "No es obligatorio estar productivo todo el tiempo", categoria = estado),
                Consejo(texto = "Una siesta de 20 minutos puede renovarte", categoria = estado),
                Consejo(texto = "Evalúa si tu cansancio es físico o mental, el enfoque del descanso cambia", categoria = estado),
                Consejo(texto = "Hidrátate bien. La deshidratación es causa común de fatiga", categoria = estado),
                Consejo(texto = "Elimina una tarea no esencial de tu lista de hoy", categoria = estado),
                Consejo(texto = "Establece un límite estricto para tu hora de dormir esta noche", categoria = estado),
                Consejo(texto = "Reduce la exposición a pantallas 30 minutos antes de acostarte", categoria = estado),
                Consejo(texto = "Come algo rico en proteínas o carbohidratos complejos para energía sostenida", categoria = estado),
                Consejo(texto = "Si no puedes dormir, simplemente túmbate y descansa los ojos", categoria = estado),
                Consejo(texto = "Recuerda que tomar descansos regulares *aumenta* la productividad a largo plazo", categoria = estado),
                Consejo(texto = "Lávate la cara con agua fría para despertar el sistema nervioso", categoria = estado),
                Consejo(texto = "Pide ayuda o delega una tarea, no tienes que hacerlo todo tú", categoria = estado),
                Consejo(texto = "Prepara tu ropa y cosas para mañana, así reduces la carga mental", categoria = estado),
                Consejo(texto = "Escucha un podcast o audiolibro en lugar de mirar la televisión", categoria = estado),
                Consejo(texto = "Sal a dar un paseo breve. La luz y el movimiento son revitalizantes", categoria = estado),
                Consejo(texto = "Pregúntate: ¿Qué es lo más importante que *debo* hacer? Lo demás puede esperar", categoria = estado),
                Consejo(texto = "Haz 5 minutos de estiramientos de cuello y espalda", categoria = estado),
                Consejo(texto = "Revisa tu nivel de estrés. El estrés crónico agota más que el trabajo", categoria = estado),
                Consejo(texto = "Asegúrate de que tu espacio de descanso sea oscuro y fresco", categoria = estado),
                Consejo(texto = "La siesta perfecta dura 20 minutos. No más, para evitar el aturdimiento", categoria = estado),
                Consejo(texto = "Permítete 'funcionar en modo lento' por el resto del día", categoria = estado),
                Consejo(texto = "Tu bienestar es tu prioridad; honra tu necesidad de descanso", categoria = estado)
            )
            else -> listOf(
                Consejo(texto = "Está bien sentirse neutral, no siempre hay que estar 'bien'", categoria = estado),
                Consejo(texto = "Observa tus emociones sin juzgarlas", categoria = estado),
                Consejo(texto = "Tal vez necesites tiempo para ti mismo", categoria = estado),
                Consejo(texto = "Aprovecha este momento para la introspección silenciosa", categoria = estado),
                Consejo(texto = "Explora un nuevo hobby o interés sin expectativas de perfección", categoria = estado),
                Consejo(texto = "Haz una lista de cosas que te gustaría aprender este año", categoria = estado),
                Consejo(texto = "Simplemente concéntrate en la tarea que tienes justo enfrente", categoria = estado),
                Consejo(texto = "Experimenta con la meditación de escaneo corporal", categoria = estado),
                Consejo(texto = "Limpia y organiza un espacio pequeño (como tu escritorio)", categoria = estado),
                Consejo(texto = "Lee un libro o artículo que no esté relacionado con el trabajo", categoria = estado),
                Consejo(texto = "Disfruta de una comida sin mirar el teléfono ni la televisión", categoria = estado),
                Consejo(texto = "Haz un dibujo o garabato libremente, sin intención", categoria = estado),
                Consejo(texto = "Sal a caminar y presta atención a 5 detalles que nunca habías notado", categoria = estado),
                Consejo(texto = "Revisa tus metas a largo plazo y haz un pequeño ajuste si es necesario", categoria = estado),
                Consejo(texto = "Contacta con un viejo amigo para una conversación casual", categoria = estado),
                Consejo(texto = "Planifica tu cena o una receta sencilla que disfrutes", categoria = estado),
                Consejo(texto = "Escucha música clásica o instrumental para calmar el ambiente", categoria = estado),
                Consejo(texto = "Evalúa si te falta nutrición, sueño o movimiento", categoria = estado),
                Consejo(texto = "Recuerda que no todas las emociones son intensas y eso es normal", categoria = estado),
                Consejo(texto = "Aprende una frase en un idioma nuevo", categoria = estado),
                Consejo(texto = "Simplifica: deshazte de tres cosas que ya no usas", categoria = estado),
                Consejo(texto = "Haz 10 minutos de jardinería o cuidado de plantas", categoria = estado),
                Consejo(texto = "La vida no requiere que estés al 100% emocionado todo el tiempo; permítete ser", categoria = estado)
            )
        }
    }}
object BancoFrasesMusicales {
    val frases = listOf(
        FraseMusical(
            nombreCancion = "Debí hacerte caso",
            frase = "No es algo malo llorar y gritarle a todo lo que existe, es culpa de las cosas que pasamos.",
            artista = "Méne"
        ),
        FraseMusical(
            nombreCancion = "Cuando veo la tele",
            frase = "Yo sé que el fondo sabe a cielo, pero tienes que hablarte mejor.",
            artista = "LMP"
        ),
        FraseMusical(
            nombreCancion = "Debí hacerte caso",
            frase = "No hay vidas malas, solo dias malos.",
            artista = "Méne"
        ),
        FraseMusical(
            nombreCancion = "Jonah Hill",
            frase = "Tal vez no es tan absurdo si lo intento otro dia, cuando todo esto no sea culpa mia.",
            artista = "Méne"
        ),
        FraseMusical(
            nombreCancion = "Cuando valoro Todo lo que es bueno",
            frase = "Si a la vida cuando sigue mas le pides que se acabe, apaga todo y salga de casa sin las llaves.",
            artista = "LMP"
        ),
        FraseMusical(
            nombreCancion = "King of Everything",
            frase = "I'll take my time to make you smile.",
            artista = "Dominic Fike"
        ),
        FraseMusical(
            nombreCancion = "Maquina (Manifiesto)",
            frase = "Yo veo la luz en ti que nadie ve, porque te quiero como nadie lo hace, pase lo que pase ahi estaré contigo hasta que no haya nadie.",
            artista = "Méne"
        ),
        FraseMusical(
            nombreCancion = "Maquina (Manifiesto)",
            frase = "Nadie te quiere como te sé querer, nadie te entiende como te sé entender, cuando sepas que no eres tan malo y que no suena tan raro lo que sueñas tener.",
            artista = "Méne"
        )
        ,
        FraseMusical(
            nombreCancion = "Maquina (Manifiesto)",
            frase = "Nadie te quiere como te sé querer, nadie te entiende como te sé entender, cuando sepas que no eres tan malo y que no suena tan raro lo que sueñas tener.",
            artista = "Méne"
        )
        ,
        FraseMusical(
            nombreCancion = "¿En qué momento?",
            frase = "Cae, prometo que esta vez no dolera, mi corazon, nunca fue tu culpa sucedió.",
            artista = "Nsqk"
        ),
                FraseMusical(
                nombreCancion = "Ojeras",
        frase = "Cambios, como petalos caidos como extraños, nada va a pasarte solo cambios, en tus ojos, en tus manos como otoño.",
        artista = "Méne"
    )
        ,
        FraseMusical(
            nombreCancion = "Moldes",
            frase = "No sé que haria si te vas, mas que sentarme a gritar y a veces lo que me mantiene vivo, es el miedo de que si me voy primero tu estarias igual.",
            artista = "Nsqk"
        )

    )
}

object BancoPlaylists {
    val playlists = listOf(
        PlaylistSpotify("Si te sientes feliz", "Cuando te sientas el rey del todo", "6F9gSOHYjQJrjqXhHvWsU5"),
        PlaylistSpotify("Si te sientes triste", "Cuando tus alas no esten escucha esto", "7qHDPIVw693o3QeDmjikG8"),
        PlaylistSpotify("Si te sientes motivado", "Cuando sientas que vuelas escucha esto", "1AxzA0GF8BbY0LpKzT3MaD"),
        PlaylistSpotify("Si te sientes diferente", "Cuando sientas los dias diferentes", "2wWzPbPNLs1xHoW8bW6V0J"),
        PlaylistSpotify("Si sientes que no puedes", "Cuando ocupes sanar tu corazon", "37pFsal4h9gTiBIMbAMV8f"),
        PlaylistSpotify("Si te sientes enojado", "Cuando te quieres como amas los lunes", "632xkfkJtpEsHqxndKeKZv"),
        PlaylistSpotify("Si te sientes nostalgico", "Cuando extrañes alguna sensacion", "4jbhZpstUVDtJdIVDbn1gF")
    )
}