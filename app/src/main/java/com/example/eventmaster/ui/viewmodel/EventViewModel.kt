package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.eventmaster.data.model.Event

class EventViewModel : ViewModel() {
    // Lista de categorías reactiva
    val categories = mutableStateListOf("Música", "Deportes", "Tecnología")
    val eventTypes = listOf("Concierto", "Conferencia", "Taller")

    // Lista de eventos reactiva
    val events = mutableStateListOf(
        Event(1, "Evento 1", "Concierto", "Música", "15 Oct 2024"),
        Event(2, "Evento 2", "Concierto", "Música", "22 Nov 2024"),
        Event(3, "Partido Final", "Taller", "Deportes", "10 Dic 2024"),
        Event(4, "Conferencia IA", "Conferencia", "Tecnología", "05 Ene 2025")
    )

    private fun nextEventId(): Int = (events.maxOfOrNull { it.id } ?: 0) + 1

    fun addCategory(name: String) {
        if (name.isNotBlank() && !categories.contains(name)) {
            categories.add(name)
        }
    }

    fun addEvent(name: String, category: String, type: String, date: String) {
        if (name.isNotBlank() && category.isNotBlank() && type.isNotBlank() && date.isNotBlank()) {
            events.add(Event(nextEventId(), name, type, category, date))
        }
    }

    fun getEventsByCategory(categoryName: String): List<Event> {
        return events.filter { it.categoria == categoryName }
    }

    fun getEventById(eventId: Int): Event? {
        return events.firstOrNull { it.id == eventId }
    }
}