package com.example.abschlussaufgabe.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.abschlussaufgabe.data.datamodel.db.Artikel

@Dao
interface ArtikelDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artikel: Artikel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(artikel:List<Artikel>)

    @Query("DELETE FROM artikel_table")
    fun deleteAll()



    @Query("SELECT * FROM artikel_table")
    fun getAll():List<Artikel>

    @Query("SELECT COUNT (*) FROM artikel_table")
    fun getCount():Int

    @Update
    fun updateArtikel(artikel: Artikel)
}