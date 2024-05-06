# RemindNotes :clipboard:

## Descripción :page_facing_up:

RemindNotes es una aplicación Android diseñada para facilitar la gestión de recordatorios y tareas diarias de manera eficiente. Con esta aplicación, puedes organizar tus tareas, eventos y citas fácilmente, asegurándote de que no se te escape nada importante.

## Características :rocket:

* **Lista de Notas y Recordatorios**: Organiza sus notas y recuerdos en categorías específicas.
* **Recordatorios Semanales**: Configura recordatorios que se retiran periódicamente a lo largo de su semana.
* **Mi día y semana**: Filtre sus recordatorios para ver los programados en ese día o en su semana.

## Instalación :wrench:

1. Clona este repositorio en tu máquina local.
```
git clone https://github.com/InfiniteNel/remind-notes-app.git
```
2. Abre el proyecto en Android Studio.

3. Construye y ejecuta la aplicación en tu dispositivo o emulador.

## Pantallas 📲

#### MainMenu
![MainMenu](https://i.imgur.com/900WV8x.png)

Menu principal de la aplicación.

### Reminder
![Reminder](https://i.imgur.com/KDqzj9X.png)

Pantalla que muestra todos los recordatorios de la aplicación.

### Notes
![Notes](https://i.imgur.com/hS04zwo.png)

Pantalla que muestra todas las notas de la aplicación.

### MyDay
![MyDay](https://i.imgur.com/ICJUdK5.png)

Pantalla que muestra los recordatorios y notas de este día, y del próximo día.

### ManageReminder
![ManageReminder](https://i.imgur.com/uZQaYnK.png)
![ManageReminder](https://i.imgur.com/rmufnIL.png)

Pantalla para crear o editar un recordatorio.

### ManageNote
![ManageNote](https://i.imgur.com/kDOARKp.png)
![ManageNote](https://i.imgur.com/RFeWB9u.png)

Pantalla para crear o editar una nota.

## Construido con 🛠️

* [Kotlin](https://developer.android.com/kotlin/learn?hl=es-419) - Lenguaje de programación principal.

### Tecnologías Utilizadas

* [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=es-419) - Mostrar la lista de recordatorios y notas.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=es-419) y [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=es-419) - Arquitectura de la aplicación.
* [BroadcastReceiver](https://developer.android.com/reference/android/content/BroadcastReceiver) - Gestión de peticiones en segundo plano. 

### Dependencias

* [NavComponent](https://developer.android.com/guide/navigation/get-started?hl=es-419) y [SafeArgs](https://developer.android.com/guide/navigation/use-graph/safe-args?hl=es-419) - Navegación entre pantallas.
* [Room](https://developer.android.com/training/data-storage/room?hl=es-419) - Almacenamiento y gestión de datos.
* [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419) - Inyección de dependencias.
