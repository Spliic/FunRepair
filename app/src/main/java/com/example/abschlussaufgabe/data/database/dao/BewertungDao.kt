package com.example.abschlussaufgabe.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abschlussaufgabe.data.datamodel.db.Bewertung

@Dao
interface BewertungDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bewertung: Bewertung)

    @Query("SELECT * FROM bewertung_table")
    fun getAll(): List<Bewertung>

    @Query("SELECT COUNT (*) FROM bewertung_table")
    fun getCount():Int


}