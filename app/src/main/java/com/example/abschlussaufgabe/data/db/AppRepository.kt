package com.example.abschlussaufgabe.data.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussaufgabe.data.datamodel.Artikel
import java.lang.Exception


class AppRepository(private val database: SortimentDatabase) {

    private val _werkzeugList = MutableLiveData<List<Artikel>>()
    val werkzeugList: LiveData<List<Artikel>>
        get() = _werkzeugList

    private val _ersatzteilList = MutableLiveData<List<Artikel>>()
    val ersatzteilList: LiveData<List<Artikel>>
        get() = _ersatzteilList

    private val _anleitungList= MutableLiveData<List<Artikel>>()
    val anleitungList: LiveData<List<Artikel>>
        get() = _anleitungList


    val werkzeuge = mutableListOf<Artikel>()
    val ersatzteile = mutableListOf<Artikel>()
    val anleitungen = mutableListOf<Artikel>()

    init {
        database.artikelDao.deleteAll()
        exampleData()

    }

    fun loadData(){
        val unsortedList = database.artikelDao.getAll()

        for (i in unsortedList){
            if (i.kategorie == "Werkzeug"){
                werkzeuge.add(i)
            } else if (i.kategorie == "Ersatzteile"){
                ersatzteile.add(i)
            } else {
                anleitungen.add(i)
            }
        }
        _werkzeugList.value = _werkzeugList.value?.plus(werkzeuge)?: werkzeuge
        _ersatzteilList.value = _ersatzteilList.value?.plus(ersatzteile) ?:ersatzteile
        _anleitungList.value = _anleitungList.value?.plus(anleitungen)?:anleitungen

    }


    fun exampleData(){
        var sortiment = mutableListOf<Artikel>()
        sortiment.add(Artikel(artikelBezeichnung = "Tool Kits", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubendreherwerkzeug", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Hebelwerkzeug", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Greifwerkzeug", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "ESD Schutz", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schneidewerkzeug", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))

        sortiment.add(Artikel(artikelBezeichnung = "Display", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "Akku", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "USB Buchse", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "Kamera", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "FaceID", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))

        sortiment.add(Artikel(artikelBezeichnung = "Display Reparatur", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Anleitung"))
        sortiment.add(Artikel(artikelBezeichnung = "Akku Reparatur", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Anleitung"))




        if (database.artikelDao.getCount() == 0){
            try {
                database.artikelDao.insertAll(sortiment)
            } catch (e: Exception){
                Log.e("AppRepository","error insert data into Database $e")
            }
        }
    }

}