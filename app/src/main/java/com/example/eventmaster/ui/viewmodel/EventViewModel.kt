package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.data.model.Category
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    // Lista de categorías reactiva (nombres)
    val categories = mutableStateListOf<String>()
    val eventTypes = listOf("Concierto", "Conferencia", "Taller")

    // Lista de eventos reactiva
    val events = mutableStateListOf<Event>()

    init {
        // colectar categorías y eventos desde base de datos
        viewModelScope.launch {
            repository.getAllCategories().collect { list ->
                categories.clear()
                categories.addAll(list.map { it.nombre })
            }
        }

        viewModelScope.launch {
            repository.getAllEvents().collect { list ->
                events.clear()
                events.addAll(list)
            }
        }
    }

    fun addCategory(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            // evitar duplicados por nombre
            if (!categories.contains(name)) {
                repository.addCategory(Category(id = 0, nombre = name, descripcion = ""))
            }
        }
    }

    fun addEvent(name: String, category: String, type: String, date: String) {
        if (name.isBlank() || category.isBlank() || type.isBlank() || date.isBlank()) return
        viewModelScope.launch {
            repository.addEvent(Event(id = 0, nombre = name, tipo = type, categoria = category, fecha = date))
        }
    }

    fun getEventsByCategory(categoryName: String): List<Event> {
        return events.filter { it.categoria == categoryName }
    }

    fun getEventById(eventId: Int): Event? {
        return events.firstOrNull { it.id == eventId }
    }
}