package com.example.abschlussaufgabe.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.data.datamodel.Bewertung


@Database(entities = [Artikel::class,Bewertung::class], version = 1)
abstract class SortimentDatabase: RoomDatabase() {

    abstract val artikelDao: ArtikelDao
    abstract val bewertungDao: BewertungDao
}

        private var INSTANCE: SortimentDatabase?= null
        fun getDatabase(context: Context):SortimentDatabase{
            synchronized(SortimentDatabase::class.java){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SortimentDatabase::class.java,
                        "sortiment database"
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }