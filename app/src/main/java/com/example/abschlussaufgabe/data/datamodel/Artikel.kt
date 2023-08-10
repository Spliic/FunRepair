package com.example.abschlussaufgabe.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artikel_table")
data class Artikel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var artikelBezeichnung: String,
    var preis: Double,
    var artikelBeschreibung: String,
    var bild: Int,
    var anleitungPdf: String,
    var kategorie: String,
    var unterkategorie: String,
    var istImWarenkorb: Boolean = false
)