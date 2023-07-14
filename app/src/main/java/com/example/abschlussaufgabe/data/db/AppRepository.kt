package com.example.abschlussaufgabe.data.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussaufgabe.R
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
        val sortiment = mutableListOf<Artikel>()
        sortiment.add(Artikel(artikelBezeichnung = "Tool Kits", preis = 0.0, artikelBeschreibung = "In unserem hochwertigen Pro Tech Toolkit findest du alle Werkzeuge, die du für die Reparatur von Smartphone, Spielekonsole oder Wearables brauchst.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubendreherwerkzeug", preis = 0.0, artikelBeschreibung = "Ob Hobby-Tüftler:in oder Reparatur-Profi – die FunRepair Schraubendreher und Bit-Sets erfreuen sich großer Beliebtheit, und das nicht ohne Grund: Sie liegen gut in der Hand, erlauben präzises Arbeiten, und wir haben für so ziemlich jeden Schraubenkopf das richtige Bit.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Hebelwerkzeug", preis = 0.0, artikelBeschreibung = "Elektronische Geräte sind empfindlich, und Komponenten wie Displays, Akkus, Motherboards, Kopfhörerstecker oder Thumbsticks sind es erst recht! Mit unseren professionellen Werkzeugen zum Öffnen und Hebeln gelingt es dir, sie sicher auszubauen.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Greifwerkzeug", preis = 0.0, artikelBeschreibung = "Saugheber, Zangen, Grips und andere Werkzeuge sind unerlässlich, um in iPhones und andere elektronische Geräte hineinzukommen, bei denen keine Eingangstür verbaut ist.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "ESD Schutz", preis = 0.0, artikelBeschreibung = "Arbeitshilfen wie unsere antistatische Arbeitsmatte, antistatische Reinigungspinsel und -bürsten und das FunRepair Anti-Statik-Armband wurden entwickelt, um elektrostatische Entladungen zu verhindern, indem sie ungleiche Spannungen verteilen oder ausgleichen, bevor sie durch empfindliche Elektronik fließen und dort Schäden anrichten können.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schneidewerkzeug", preis = 0.0, artikelBeschreibung = "Keine Lust, stundenlang nach dem perfekten Tool zu suchen? Wir haben die Recherche für dich übernommen und eine breite Auswahl an hochwertigen Schneidwerkzeugen zusammengestellt.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))

        sortiment.add(Artikel(artikelBezeichnung = "Display Kit's", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "Akku Kit's", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "USB Buchse Kit's", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "Kamera Kit's", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))
        sortiment.add(Artikel(artikelBezeichnung = "FaceID Kit's", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Ersatzteile"))

        sortiment.add(Artikel(artikelBezeichnung = "Display Anleitung", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Anleitung"))
        sortiment.add(Artikel(artikelBezeichnung = "Akku Anleitung", preis = 0.0, artikelBeschreibung = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et", bild = 0, anleitungPdf = "", kategorie = "Anleitung"))




        if (database.artikelDao.getCount() == 0){
            try {
                database.artikelDao.insertAll(sortiment)
            } catch (e: Exception){
                Log.e("AppRepository","error insert data into Database $e")
            }
        }
    }

}