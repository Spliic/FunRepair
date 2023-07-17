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

        // Werkzeug Kategorie
        val sortiment = mutableListOf<Artikel>()
        sortiment.add(Artikel(artikelBezeichnung = "Tool Kits", preis = 0.0, artikelBeschreibung = "In unserem hochwertigen  Toolkit's findest du alle Werkzeuge, die du für die Reparatur von Smartphone brauchst.", bild = R.drawable.kategoriewerkzeugtoolkit, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubendreherwerkzeug", preis = 0.0, artikelBeschreibung = "Ob Hobby-Tüftler:in oder Reparatur-Profi – die FunRepair Schraubendreher und Bit-Sets erfreuen sich großer Beliebtheit, und das nicht ohne Grund: Sie liegen gut in der Hand, erlauben präzises Arbeiten, und wir haben für so ziemlich jeden Schraubenkopf das richtige Bit.", bild = R.drawable.kategoriewerkzeugdreher, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Hebelwerkzeug", preis = 0.0, artikelBeschreibung = "Elektronische Geräte sind empfindlich, und Komponenten wie Displays, Akkus, Motherboards, Kopfhörerstecker oder Thumbsticks sind es erst recht! Mit unseren professionellen Werkzeugen zum Öffnen und Hebeln gelingt es dir, sie sicher auszubauen.", bild = R.drawable.kategoriewerkzeughebel, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Greifwerkzeug", preis = 0.0, artikelBeschreibung = "Saugheber, Zangen, Grips und andere Werkzeuge sind unerlässlich, um in iPhones und andere elektronische Geräte hineinzukommen, bei denen keine Eingangstür verbaut ist.", bild = R.drawable.kategoriewerkzeuggreif, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "ESD Schutz", preis = 0.0, artikelBeschreibung = "Arbeitshilfen wie unsere antistatische Arbeitsmatte, antistatische Reinigungspinsel und -bürsten und das FunRepair Anti-Statik-Armband wurden entwickelt, um elektrostatische Entladungen zu verhindern, indem sie ungleiche Spannungen verteilen oder ausgleichen, bevor sie durch empfindliche Elektronik fließen und dort Schäden anrichten können.", bild = R.drawable.kategoriewerkzeugschutz, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schneidewerkzeug", preis = 0.0, artikelBeschreibung = "Keine Lust, stundenlang nach dem perfekten Tool zu suchen? Wir haben die Recherche für dich übernommen und eine breite Auswahl an hochwertigen Schneidwerkzeugen zusammengestellt.", bild = R.drawable.kategoriewerkzeugschneide, anleitungPdf = "", kategorie = "Werkzeug"))

        // Sortiment Werkzeug Tool Kits

        sortiment.add(Artikel(artikelBezeichnung = "Pro Tech Toolkit", preis = 74.99, artikelBeschreibung = "In unserem hochwertigen Pro Tech Toolkit findest du alle Werkzeuge, die du für die Reparatur von Smartphone brauchst.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "iOpener", preis = 12.99, artikelBeschreibung = "Mit dem iOpener löst du problemlos und schnell Klebeverbindungen, ohne das Risiko, mit einer Heißluftpistole und zu viel Hitze dein Gerät zu beschädigen. ", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Essential Electronics Toolkit", preis = 29.99, artikelBeschreibung = "Unser Essential Electronics Toolkit bringt deswegen alles mit, was du für die meisten Elektronikreparaturen benötigst. Kompakt und immer griffbereit.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Repair Business Toolkit", preis = 299.99, artikelBeschreibung = "Eine komplette Reparaturwerkstatt in einer einzigen Tasche. Unser ultimatives Profi-Werkzeugset wurde 2023 komplett überarbeitet und enthält bewährte, hochwertige Werkzeuge, die sorgfältig mit der neuesten und besten Reparaturtechnologie zusammengestellt wurden.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Pro Tech Diagnose", preis = 69.99, artikelBeschreibung = "Am besten untersuchst du die Ursache mit unserem Pro Tech Diagnose Kit, um deine Fehlersuche sicher und fachmännisch durchzuführen.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))

        // Sortiment Werkzeug Schraubendreherwerkzeug


        sortiment.add(Artikel(artikelBezeichnung = "4MM Schraubendreher-Bit", preis = 2.95, artikelBeschreibung = "Die FunRepair 4 mm Präzisions-Bits wurden mit einem längeren Schaft konzipiert, um eine größere und präzisere Reichweite zu ermöglichen", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "4mm Bithalter aus Aluminium", preis = 9.95, artikelBeschreibung = "Bithalter aus Aluminium passend für 4 mm Bits.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "P5 Pentalobe Schraubendreher", preis = 5.95, artikelBeschreibung = "Dein präziser P5 Pentalobe Schraubendreher, um dein iPhone zu öffnen.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubenentferner-Set", preis = 19.95, artikelBeschreibung = "Entferne rundgedrehte Schrauben ohne Beschädigungen am Gerät.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Elektrischer Schraubendreher", preis = 19.95, artikelBeschreibung = "Vorhang auf für den kabellosen elektrischen Präzisionsschraubendreher. Er passt perfekt zu den 4 mm Precision Bit Sets von FunRepair und erleichtert dir jedes Projekt, bei dem du viele Schrauben rein- und rausdrehen musst.", bild = 0, anleitungPdf = "", kategorie = "Werkzeug"))












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