package com.example.eventmaster.data.repository

import com.example.eventmaster.data.local.database.EventMasterDatabase
import com.example.eventmaster.data.local.mappers.toDomain
import com.example.eventmaster.data.local.mappers.toEntity
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.data.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepository(private val db: EventMasterDatabase) {
    fun getAllEvents(): Flow<List<Event>> = db.eventDao().getAll().map { list -> list.map { it.toDomain() } }

    fun getEventsByCategory(categoryName: String): Flow<List<Event>> = db.eventDao().getByCategory(categoryName).map { list -> list.map { it.toDomain() } }

    suspend fun getEventById(id: Int): Event? = db.eventDao().getById(id)?.toDomain()

    suspend fun addEvent(event: Event) {
        db.eventDao().insert(event.toEntity())
    }

    suspend fun deleteEvent(event: Event) {
        db.eventDao().delete(event.toEntity())
    }

    fun getAllCategories(): Flow<List<Category>> = db.categoryDao().getAll().map { list -> list.map { it.toDomain() } }

    suspend fun addCategory(category: Category) {
        db.categoryDao().insert(category.toEntity())
    }

    suspend fun deleteCategory(category: Category) {
        db.categoryDao().delete(category.toEntity())
    }
}


