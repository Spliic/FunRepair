package com.example.abschlussaufgabe.data.datamodel.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bewertung_table")
data class Bewertung(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,


    var bewertung: String,
    var bewertungSterne: Int,
    var bewertungsBild: String,
    var artikelId: Int
)