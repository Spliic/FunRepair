package com.example.abschlussaufgabe.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abschlussaufgabe.data.datamodel.Artikel

@Dao
interface ArtikelDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artikel:Artikel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(artikel:List<Artikel>)

    @Query("DELETE FROM artikel_table")
    fun deleteAll()


    @Query("SELECT * FROM artikel_table")
    fun getAll():List<Artikel>

    @Query("SELECT COUNT (*) FROM artikel_table")
    fun getCount():Int
}