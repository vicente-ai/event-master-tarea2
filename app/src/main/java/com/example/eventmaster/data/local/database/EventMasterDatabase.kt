package com.example.eventmaster.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eventmaster.data.local.dao.CategoryDao
import com.example.eventmaster.data.local.dao.EventDao
import com.example.eventmaster.data.local.entity.CategoryEntity
import com.example.eventmaster.data.local.entity.EventEntity
import kotlinx.coroutines.flow.firstOrNull

@Database(entities = [EventEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class EventMasterDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: EventMasterDatabase? = null

        fun getInstance(context: Context): EventMasterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventMasterDatabase::class.java,
                    "event_master_db"
                ).build()
                INSTANCE = instance

                // Pre-populate default categories and sample events if empty
                // Run in background executor to avoid blocking
                java.util.concurrent.Executors.newSingleThreadExecutor().execute {
                    try {
                        // Use runBlocking to collect Flow first values
                        kotlinx.coroutines.runBlocking {
                            val cats = instance.categoryDao().getAll().firstOrNull()
                            if (cats == null || cats.isEmpty()) {
                                instance.categoryDao().insert(com.example.eventmaster.data.local.entity.CategoryEntity(nombre = "Música", descripcion = ""))
                                instance.categoryDao().insert(com.example.eventmaster.data.local.entity.CategoryEntity(nombre = "Deportes", descripcion = ""))
                                instance.categoryDao().insert(com.example.eventmaster.data.local.entity.CategoryEntity(nombre = "Tecnología", descripcion = ""))
                            }

                            val evs = instance.eventDao().getAll().firstOrNull()
                            if (evs == null || evs.isEmpty()) {
                                instance.eventDao().insert(com.example.eventmaster.data.local.entity.EventEntity(nombre = "Evento 1", tipo = "Concierto", categoria = "Música", fecha = "15 Oct 2024"))
                                instance.eventDao().insert(com.example.eventmaster.data.local.entity.EventEntity(nombre = "Evento 2", tipo = "Concierto", categoria = "Música", fecha = "22 Nov 2024"))
                                instance.eventDao().insert(com.example.eventmaster.data.local.entity.EventEntity(nombre = "Partido Final", tipo = "Taller", categoria = "Deportes", fecha = "10 Dic 2024"))
                                instance.eventDao().insert(com.example.eventmaster.data.local.entity.EventEntity(nombre = "Conferencia IA", tipo = "Conferencia", categoria = "Tecnología", fecha = "05 Ene 2025"))
                            }
                        }
                    } catch (_: Exception) {
                        // ignore seeding failures
                    }
                }

                instance
            }
        }
    }
}



