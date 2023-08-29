package com.example.abschlussaufgabe.data.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.data.apiServices.WetterApi
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.data.datamodel.CurrentWeather
import java.lang.Exception


class AppRepository(private val database: SortimentDatabase, private val wetterApi: WetterApi) {

    /**
     * Der Code verwaltet aktuelle Wetterdaten mithilfe von LiveData und Retrofit.
     * Die Funktion getWetter ruft Wetterdaten ab und speichert sie in _currentWetter, während Fehler abgefangen und protokolliert werden.
     */
    private val _currentWetter = MutableLiveData<CurrentWeather>()
        val currentWetter: LiveData<CurrentWeather>
            get() = _currentWetter
    suspend fun getWetter(){
        try {
            var test = wetterApi.retrofitService.getWetter()
            _currentWetter.value = test
        } catch (e: Exception){
            Log.e("AppRepository","Error Weather API DATA: ${e.message}")
        }
    }


    /**
     * Es werden drei MutableLiveData-Objekte erstellt: _werkzeugList, _ersatzteilList und _anleitungList, die Listen von Artikel-Objekten speichern.
     */
    private val _werkzeugList = MutableLiveData<List<Artikel>>()
    val werkzeugList: LiveData<List<Artikel>>
        get() = _werkzeugList

    private val _ersatzteilList = MutableLiveData<List<Artikel>>()
    val ersatzteilList: LiveData<List<Artikel>>
        get() = _ersatzteilList

    private val _anleitungList= MutableLiveData<List<Artikel>>()
    val anleitungList: LiveData<List<Artikel>>
        get() = _anleitungList


    /**
     * Die Variable completeSortimentList wird mit der Methode getAll() des artikelDao aus der Datenbank initialisiert.
     * Diese Methode ruft alle Artikel aus der Datenbank ab und speichert sie in der Liste completeSortimentList.
     */
    val completeSortimentList: List<Artikel> = database.artikelDao.getAll()


    /**
     * Hier erstelle ich leere MutableListen vom Model Artikel
     */
    val werkzeuge = mutableListOf<Artikel>()
    val ersatzteile = mutableListOf<Artikel>()
    val anleitungen = mutableListOf<Artikel>()



    /**
     * Im Konstruktor werden alle vorhandenen Daten in der Datenbanktabelle gelöscht und Beispiel-Daten eingefügt.
     */
    init {
        exampleData()
    }


    /**
     * Die Funktion loadData() lädt Daten aus einer Datenbank und verteilt sie in verschiedene Listen basierend auf der Kategorie jedes Elements.
     */
    fun loadData(){
        val unsortedList = database.artikelDao.getAll()

        for (i in unsortedList){
            if (i.kategorie == "Werkzeug"){
                werkzeuge.add(i)
            } else if (i.kategorie == "Ersatzteile"){
                ersatzteile.add(i)
            } else if (i.kategorie == "Anleitung"){
                anleitungen.add(i)
            } else {

            }
        }
        _werkzeugList.value = _werkzeugList.value?.plus(werkzeuge)?: werkzeuge
        _ersatzteilList.value = _ersatzteilList.value?.plus(ersatzteile) ?:ersatzteile
        _anleitungList.value = _anleitungList.value?.plus(anleitungen)?:anleitungen
    }
    
    fun editArtikel(artikel: Artikel){
        database.artikelDao.updateArtikel(artikel)
    }


    /**
     * Sortiment was in die Datenbank befüllt wird.
     */
    fun exampleData(){

        // Werkzeug Kategorie
        val sortiment = mutableListOf<Artikel>()
        sortiment.add(Artikel(artikelBezeichnung = "Tool Kits", preis = 0.0, artikelBeschreibung = "In unserem hochwertigen  Toolkit's findest du alle Werkzeuge, die du für die Reparatur von Smartphone brauchst.", bild = R.drawable.kategoriewerkzeugtoolkit, anleitungPdf = "", kategorie = "Werkzeug", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubendreherwerkzeug", preis = 0.0, artikelBeschreibung = "Ob Hobby-Tüftler:in oder Reparatur-Profi – die FunRepair Schraubendreher und Bit-Sets erfreuen sich großer Beliebtheit, und das nicht ohne Grund: Sie liegen gut in der Hand, erlauben präzises Arbeiten, und wir haben für so ziemlich jeden Schraubenkopf das richtige Bit.", bild = R.drawable.kategoriewerkzeugdreher, anleitungPdf = "", kategorie = "Werkzeug", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Hebelwerkzeug", preis = 0.0, artikelBeschreibung = "Elektronische Geräte sind empfindlich, und Komponenten wie Displays, Akkus, Motherboards, Kopfhörerstecker oder Thumbsticks sind es erst recht! Mit unseren professionellen Werkzeugen zum Öffnen und Hebeln gelingt es dir, sie sicher auszubauen.", bild = R.drawable.kategoriewerkzeughebel, anleitungPdf = "", kategorie = "Werkzeug", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Greifwerkzeug", preis = 0.0, artikelBeschreibung = "Saugheber, Zangen, Grips und andere Werkzeuge sind unerlässlich, um in iPhones und andere elektronische Geräte hineinzukommen, bei denen keine Eingangstür verbaut ist.", bild = R.drawable.kategoriewerkzeuggreif, anleitungPdf = "", kategorie = "Werkzeug", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "ESD Schutz", preis = 0.0, artikelBeschreibung = "Arbeitshilfen wie unsere antistatische Arbeitsmatte, antistatische Reinigungspinsel und -bürsten und das FunRepair Anti-Statik-Armband wurden entwickelt, um elektrostatische Entladungen zu verhindern, indem sie ungleiche Spannungen verteilen oder ausgleichen, bevor sie durch empfindliche Elektronik fließen und dort Schäden anrichten können.", bild = R.drawable.kategoriewerkzeugschutz, anleitungPdf = "", kategorie = "Werkzeug", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Schneidewerkzeug", preis = 0.0, artikelBeschreibung = "Keine Lust, stundenlang nach dem perfekten Tool zu suchen? Wir haben die Recherche für dich übernommen und eine breite Auswahl an hochwertigen Schneidwerkzeugen zusammengestellt.", bild = R.drawable.kategoriewerkzeugschneide, anleitungPdf = "", kategorie = "Werkzeug", unterkategorie = ""))

        // Sortiment Werkzeug Tool Kits

        sortiment.add(Artikel(artikelBezeichnung = "Pro Tech Toolkit", preis = 74.99, artikelBeschreibung = "In unserem hochwertigen Pro Tech Toolkit findest du alle Werkzeuge, die du für die Reparatur von Smartphone brauchst.", bild = R.drawable.toolkits, anleitungPdf = "", kategorie = "", unterkategorie = "Tool Kits"))
        sortiment.add(Artikel(artikelBezeichnung = "iOpener", preis = 12.99, artikelBeschreibung = "Mit dem iOpener löst du problemlos und schnell Klebeverbindungen, ohne das Risiko, mit einer Heißluftpistole und zu viel Hitze dein Gerät zu beschädigen. ", bild = R.drawable.toolkitsiopener, anleitungPdf = "", kategorie = "", unterkategorie = "Tool Kits"))
        sortiment.add(Artikel(artikelBezeichnung = "Essential Electronics Toolkit", preis = 29.99, artikelBeschreibung = "Unser Essential Electronics Toolkit bringt deswegen alles mit, was du für die meisten Elektronikreparaturen benötigst. Kompakt und immer griffbereit.", bild = R.drawable.toolkitsessential, anleitungPdf = "", kategorie = "", unterkategorie = "Tool Kits"))
        sortiment.add(Artikel(artikelBezeichnung = "Repair Business Toolkit", preis = 299.99, artikelBeschreibung = "Eine komplette Reparaturwerkstatt in einer einzigen Tasche. Unser ultimatives Profi-Werkzeugset wurde 2023 komplett überarbeitet und enthält bewährte, hochwertige Werkzeuge, die sorgfältig mit der neuesten und besten Reparaturtechnologie zusammengestellt wurden.", bild = R.drawable.toolkitsbusiness, anleitungPdf = "", kategorie = "", unterkategorie = "Tool Kits"))
        sortiment.add(Artikel(artikelBezeichnung = "Pro Tech Diagnose", preis = 69.99, artikelBeschreibung = "Am besten untersuchst du die Ursache mit unserem Pro Tech Diagnose Kit, um deine Fehlersuche sicher und fachmännisch durchzuführen.", bild = R.drawable.toolkitsdiagnose, anleitungPdf = "", kategorie = "", unterkategorie = "Tool Kits"))

        // Sortiment Werkzeug Schraubendreherwerkzeug

        sortiment.add(Artikel(artikelBezeichnung = "4MM Schraubendreher-Bit", preis = 2.95, artikelBeschreibung = "Die FunRepair 4 mm Präzisions-Bits wurden mit einem längeren Schaft konzipiert, um eine größere und präzisere Reichweite zu ermöglichen", bild = R.drawable.schraubendreherbit4mm, anleitungPdf = "", kategorie = "", unterkategorie = "Schraubendreherwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "4mm Bithalter aus Aluminium", preis = 9.95, artikelBeschreibung = "Bithalter aus Aluminium passend für 4 mm Bits.", bild = R.drawable.schraubendreherbithalter4mm, anleitungPdf = "", kategorie = "", unterkategorie = "Schraubendreherwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "P5 Pentalobe Schraubendreher", preis = 5.95, artikelBeschreibung = "Dein präziser P5 Pentalobe Schraubendreher, um dein iPhone zu öffnen.", bild = R.drawable.schraubendreherpentalobe, anleitungPdf = "", kategorie = "", unterkategorie = "Schraubendreherwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubenentferner-Set", preis = 19.95, artikelBeschreibung = "Entferne rundgedrehte Schrauben ohne Beschädigungen am Gerät.", bild = R.drawable.schraubendreherentfernerset, anleitungPdf = "", kategorie = "", unterkategorie = "Schraubendreherwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Elektrischer Schraubendreher", preis = 19.95, artikelBeschreibung = "Vorhang auf für den kabellosen elektrischen Präzisionsschraubendreher. Er passt perfekt zu den 4 mm Precision Bit Sets von FunRepair und erleichtert dir jedes Projekt, bei dem du viele Schrauben rein- und rausdrehen musst.", bild = R.drawable.schraubendreherelektrischer, anleitungPdf = "", kategorie = "", unterkategorie = "Schraubendreherwerkzeug"))

        // Sortiment Werkzeug Hebelwerkzeug

        sortiment.add(Artikel(artikelBezeichnung = "Werkzeugset zum Hebeln", preis = 9.95, artikelBeschreibung = "Besser, du greifst direkt zum passenden Spezialwerkzeug aus biegsamem aber robustem Kunststoff – genau wie wir es im Werkzeugset zum Hebeln und Öffnen zusammengestellt haben.", bild = R.drawable.hebelset, anleitungPdf = "", kategorie = "", unterkategorie = "Hebelwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Hebelfreund Jimmy", preis = 7.95, artikelBeschreibung = "Dabei muss das gar nicht sein, wenn du nur unseren Jimmy griffbereit hast – denn mit diesem praktischen Helfer im Hosentaschenformat bekommst du fast jedes elektronische Gerät mühelos auf.", bild = R.drawable.hebeljimmy, anleitungPdf = "", kategorie = "", unterkategorie = "Hebelwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Anti Clamp Hebelkit", preis = 24.95, artikelBeschreibung = "Diese Klemme heißt Anti-Clamp (liebevoll Clampy genannt), die wir zur neuen Königin der Geräteöffnung gekrönt haben. Die Herrscherin über den gläsernen Thron, wenn du so willst.", bild = R.drawable.hebelanticlamp, anleitungPdf = "", kategorie = "", unterkategorie = "Hebelwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "iSesamo Opening Tool", preis = 9.95, artikelBeschreibung = "Die Klinge aus Federstahl besitzt eine clevere Hebelspitze und ein besonders rutschfestes Griffstück.", bild = R.drawable.hebelisesamo, anleitungPdf = "", kategorie = "", unterkategorie = "Hebelwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Steinel Heißluftgebläse", preis = 34.95, artikelBeschreibung = "Bestens geeignet zum Aufschmelzen von Lötverbindungen und Klebstoffen", bild = R.drawable.hebelsteinel, anleitungPdf = "", kategorie = "", unterkategorie = "Hebelwerkzeug"))

        // Sortiment Werkzeug Greifwerkzeug

        sortiment.add(Artikel(artikelBezeichnung = "Pinzette mit Kunststoffspitzen", preis = 4.95, artikelBeschreibung = "Speziell geeignet für kleine und empfindliche Komponenten. ESD-sichere Pinzette mit einer auswechselbaren Spitze aus abriebfestem Kunststoff, perfekt für das Hantieren mit empfindlicher Elektronik geeignet.", bild = R.drawable.greifpinzette, anleitungPdf = "", kategorie = "", unterkategorie = "Greifwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Metall Pinzette", preis = 4.95, artikelBeschreibung = "Die Pinzetten aus rostfreiem Edelstahl sind mit einer ESD-sicheren Beschichtung versehen und können in verschiedenen Ausführungen (stumpf, spitz oder gebogen) bestellt werden.", bild = R.drawable.greifpinzettemetall, anleitungPdf = "", kategorie = "", unterkategorie = "Greifwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Metall Pinzetten Set", preis = 14.95, artikelBeschreibung = "Drei Spezial-Pinzetten haben wir für dich ausgewählt und zu einem Kit zusammengestellt. So ausgerüstet bereitet dir zukünftig auch das feinste Kabel keinerlei Kopfzerbrechen mehr.", bild = R.drawable.greifpinzetteset, anleitungPdf = "", kategorie = "", unterkategorie = "Greifwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Magnetgreifer", preis = 5.95, artikelBeschreibung = "Ideal, um kleine Schrauben aus engen Zwischenräumen zu angeln! Kann auch dazu benutzt werden, den Schaft deines Schraubendrehers zeitweise zu magnetisieren, indem du den Magnetgreifer dagegen hältst.", bild = R.drawable.greifermagnet, anleitungPdf = "", kategorie = "", unterkategorie = "Greifwerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Schraubenlösende Zange", preis = 19.95, artikelBeschreibung = "Speziell designte Zähne greifen auch kleine Schraubenköpfe, Bolzen oder auch abgebrochene Schrauben, um sie zu entfernen.", bild = R.drawable.greiferfuerschrauben, anleitungPdf = "", kategorie = "", unterkategorie = "Greifwerkzeug"))

        // Sortiment Werkzeug Schutz

        sortiment.add(Artikel(artikelBezeichnung = "Faltbare Antistatik-Matte", preis = 29.95, artikelBeschreibung = "Damit dieses Horrorszenario niemals eintritt und deine Elektronikgeräte nicht als teure Türstopper enden, empfehlen wir dir Elektronikreparaturen immer auf unserer faltbaren Antistatik-Matte durchzuführen. ", bild = R.drawable.schutzmatte, anleitungPdf = "", kategorie = "", unterkategorie = "ESD Schutz"))
        sortiment.add(Artikel(artikelBezeichnung = "ESD Handschuhe", preis = 9.99, artikelBeschreibung = "Hochwertige, weiche ESD Handschuhe für den Umgang mit empfindlichen Komponenten.", bild = R.drawable.schutzhandschuhe, anleitungPdf = "", kategorie = "", unterkategorie = "ESD Schutz"))
        sortiment.add(Artikel(artikelBezeichnung = "Antistatik-Armband", preis = 4.95, artikelBeschreibung = "Schützt deine Elektronik vor Schäden durch elektrostatische Aufladung (ESD) während der Reparatur.", bild = R.drawable.schutzband, anleitungPdf = "", kategorie = "", unterkategorie = "ESD Schutz"))


        // Sortiment Werkzeug Schneide

        sortiment.add(Artikel(artikelBezeichnung = "Seitenschneider CHP-170", preis = 7.95, artikelBeschreibung = "C.H.P Werkzeuge werden in Italien hergestellt und kommen vor allem bei professionellen Anwendern und in der Industrie zum Einsatz.", bild = R.drawable.schneideseiten, anleitungPdf = "", kategorie = "", unterkategorie = "Schneidewerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Gebogene Rasierklinge", preis = 4.95, artikelBeschreibung = "Die große gebogene Rasierklinge verfügt über einen handlichen Griff, damit du die Klinge gezielt ansetzen kannst.", bild = R.drawable.schneideklinge, anleitungPdf = "", kategorie = "", unterkategorie = "Schneidewerkzeug"))
        sortiment.add(Artikel(artikelBezeichnung = "Cutter", preis = 4.95, artikelBeschreibung = "Cuttermesser für den täglichen Einsatz", bild = R.drawable.schneidecutter, anleitungPdf = "", kategorie = "", unterkategorie = "Schneidewerkzeug"))







        // Kategorie Ersatzteile

        sortiment.add(Artikel(artikelBezeichnung = "Display Kit's", preis = 0.0, artikelBeschreibung = "FunRepair's hochwertige iPhone Ersatzteile werden nach strengen Kriterien geprüft und sind von unserer Qualitätsgarantie abgesichert. ", bild = R.drawable.ersatzteiledisplay, anleitungPdf = "", kategorie = "Ersatzteile", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Akku Kit's", preis = 0.0, artikelBeschreibung = "FunRepair's hochwertige iPhone Ersatzteile werden nach strengen Kriterien geprüft und sind von unserer Qualitätsgarantie abgesichert.", bild = R.drawable.ersatzteileakku, anleitungPdf = "", kategorie = "Ersatzteile", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Anschlüsse", preis = 0.0, artikelBeschreibung = "FunRepair's hochwertige iPhone Ersatzteile werden nach strengen Kriterien geprüft und sind von unserer Qualitätsgarantie abgesichert.", bild = R.drawable.ersatzteileanschluss, anleitungPdf = "", kategorie = "Ersatzteile", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Buttons", preis = 0.0, artikelBeschreibung = "FunRepair's hochwertige iPhone Ersatzteile werden nach strengen Kriterien geprüft und sind von unserer Qualitätsgarantie abgesichert.", bild = R.drawable.ersatzteilebutton, anleitungPdf = "", kategorie = "Ersatzteile", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Gehäusekomponenten", preis = 0.0, artikelBeschreibung = "FunRepair's hochwertige iPhone Ersatzteile werden nach strengen Kriterien geprüft und sind von unserer Qualitätsgarantie abgesichert.", bild = R.drawable.ersatzteilegehause, anleitungPdf = "", kategorie = "Ersatzteile", unterkategorie = ""))


        // Sortiment Display Kit's

        sortiment.add(Artikel(artikelBezeichnung = "iPhone 8 Display", preis = 74.95, artikelBeschreibung = "Auf diesem iPhone 8 Display sind alle kleinen Komponenten bereits vorinstalliert - das spart Zeit und Nerven bei der Reparatur.", bild = R.drawable.display8, anleitungPdf = "", kategorie = "", unterkategorie = "Display Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Display", preis = 99.95, artikelBeschreibung = "Tausche ein verkratztes oder gesplittertes Frontpanel aus Glas mit Touchscreen oder ein defektes \"Liquid Retina\" LCD Display. Dieses Teil ist kompatibel mit einem iPhone 11.", bild = R.drawable.display11, anleitungPdf = "", kategorie = "", unterkategorie = "Display Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Pro Display", preis = 129.95, artikelBeschreibung = "Ersetze ein gebrochenes Front Panel aus Glas oder ein defektes OLED Display an deinem iPhone 11 Pro.", bild = R.drawable.display11pro, anleitungPdf = "", kategorie = "", unterkategorie = "Display Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Pro Max Display(OLED)", preis = 169.95, artikelBeschreibung = "Ersetze ein verkratztes oder gesplittertes Frontpanel aus Glas mit Touchscreen oder ein defektes \"Super Retina XDR\" OLED Display. Dieses Teil ist kompatibel mit einem iPhone 11 Pro Max.", bild = R.drawable.display11promax, anleitungPdf = "", kategorie = "", unterkategorie = "Display Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 12/12Pro Display(OLED)", preis = 189.95, artikelBeschreibung = "Ersetze ein verkratztes oder gebrochenes Front Panel aus Glas mit Touchscreen oder ein defektes Super Retina XDR OLED Display. Dieses Ersatzteil ist mit dem iPhone 12 und dem iPhone 12 Pro kompatibel.", bild = R.drawable.display12, anleitungPdf = "", kategorie = "", unterkategorie = "Display Kit's"))

        // Sortiment Akku


        sortiment.add(Artikel(artikelBezeichnung = "iPhone 8 Akku", preis = 24.95, artikelBeschreibung = "Dieser iPhone 8 Ersatzakku ist genau das Richtige, um dein kaputtes iPhone 8 wieder zum Laufen zu bringen!", bild = R.drawable.akku8, anleitungPdf = "", kategorie = "", unterkategorie = "Akku Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Akku", preis = 39.95, artikelBeschreibung = "Mit diesem iPhone 11 Austauschakku bringst du dein iPhone 11 wieder zum Laufen!", bild = R.drawable.akku11, anleitungPdf = "", kategorie = "", unterkategorie = "Akku Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Pro Akku", preis = 49.95, artikelBeschreibung = "Mit diesem Austauschakku kannst du dein defektes iPhone 11 Pro wieder zum Laufen bringen!", bild = R.drawable.akku11pro, anleitungPdf = "", kategorie = "", unterkategorie = "Akku Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Pro Max Akku", preis = 54.95, artikelBeschreibung = "Dieser Austauschakku bringt dein kaputtes iPhone 11 Pro Max wieder zum Laufen!", bild = R.drawable.akku11promax, anleitungPdf = "", kategorie = "", unterkategorie = "Akku Kit's"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 12 Pro Max Akku", preis = 54.95, artikelBeschreibung = "Dieser iPhone 12 Pro Max Ersatzakku ist genau das Richtige, um dein iPhone wieder zum Laufen zu bringen!", bild = R.drawable.akku12, anleitungPdf = "", kategorie = "", unterkategorie = "Akku Kit's"))

        // Sortiment Anschlüsse

        sortiment.add(Artikel(artikelBezeichnung = "iPhone 11 Pro Max Connector Einheit", preis = 99.95, artikelBeschreibung = "Ersetze den Lightning Connector und das Mikrofon in deinem iPhone 11 Pro Max.", bild = R.drawable.anschluss11, anleitungPdf = "", kategorie = "", unterkategorie = "Anschlüsse"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 5c Lightning Connector", preis = 14.95, artikelBeschreibung = "Die Baugruppe aus Lightning Connector und Kopfhörerbuchse für das iPhone 5c ist die Lösung für Verbindungsprobleme oder Schwierigkeiten beim Laden.", bild = R.drawable.anschluss5, anleitungPdf = "", kategorie = "", unterkategorie = "Anschlüsse"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone SE Lightning Connector", preis = 19.95, artikelBeschreibung = "Dein iPhone SE (1. Generation) lädt nicht mehr? Das Mikrofon funktioniert nicht? Dieses Ersatzteil kann die Lösung sein!", bild = R.drawable.anschlussse, anleitungPdf = "", kategorie = "", unterkategorie = "Anschlüsse"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 8 Plus Lightning Connector", preis = 24.95, artikelBeschreibung = "Du hast Probleme mit dem Laden oder mit der Verbindung? Wir haben die Lösung!", bild = R.drawable.anschluss8, anleitungPdf = "", kategorie = "", unterkategorie = "Anschlüsse"))

        // Sortiment Buttons

        sortiment.add(Artikel(artikelBezeichnung = "iPhone 7/ 7 Plus / 8 / 8 Plus Home Button", preis = 19.95, artikelBeschreibung = "Ersetze die Home Button Einheit in deinem iPhone 7/7 Plus/8/8 Plus und repariere so einen kaputten Home Button.", bild = R.drawable.button7, anleitungPdf = "", kategorie = "", unterkategorie = "Buttons"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 7 Gehäusetasten Set", preis = 0.99, artikelBeschreibung = "Dieses Set beinhaltet alle äusseren Gehäusetasten: die Power/Lock Taste, die Lautstärke-Regler und die Stummtaste, damit du die Tasten mit Schönheitsfehlern austauschen kannst.", bild = R.drawable.button7gehause, anleitungPdf = "", kategorie = "", unterkategorie = "Buttons"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 5C Audiosteuerung", preis = 2.95, artikelBeschreibung = "Wenn die Lautstärkeregler oder der Power Button nicht mehr reagieren oder der Stummschalter nicht mehr richtig funktioniert, ist wahrscheinlich dieses Ersatzteil das Richtige!", bild = R.drawable.button5audio, anleitungPdf = "", kategorie = "", unterkategorie = "Buttons"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 5 Volume Button", preis = 0.95, artikelBeschreibung = "Es handelt sich hierbei nur um den sichtbaren Kunststoff-Schalter.", bild = R.drawable.button5volume, anleitungPdf = "", kategorie = "", unterkategorie = "Buttons"))


        // Sortiment Gehäuse komponente


        sortiment.add(Artikel(artikelBezeichnung = "iPhone X Insight Case", preis = 9.95, artikelBeschreibung = "Wir wissen ja, wie gerne du das Innenleben deines iPhones mit unseren Teardown Hintergrundbildern zur Schau stellst. Wir wissen aber auch, dass es dir noch wichtiger ist, dass dein iPhone gut funktioniert.", bild = R.drawable.hausx, anleitungPdf = "", kategorie = "", unterkategorie = "Gehäusekomponenten"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 6s Insight Case", preis = 9.95, artikelBeschreibung = "Wir wissen ja, wie gerne du das Innenleben deines iPhones mit unseren Teardown Hintergrundbildern zur Schau stellst. Wir wissen aber auch, dass es dir noch wichtiger ist, dass dein iPhone gut funktioniert.", bild = R.drawable.haus6s, anleitungPdf = "", kategorie = "", unterkategorie = "Gehäusekomponenten"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 8 Insight Case", preis = 9.95, artikelBeschreibung = "Wir wissen ja, wie gerne du das Innenleben deines iPhones mit unseren Teardown Hintergrundbildern zur Schau stellst. Wir wissen aber auch, dass es dir noch wichtiger ist, dass dein iPhone gut funktioniert.", bild = R.drawable.haus8, anleitungPdf = "", kategorie = "", unterkategorie = "Gehäusekomponenten"))
        sortiment.add(Artikel(artikelBezeichnung = "iPhone 7 Insight Case", preis = 9.95, artikelBeschreibung = "Wir wissen ja, wie gerne du das Innenleben deines iPhones mit unseren Teardown Hintergrundbildern zur Schau stellst. Wir wissen aber auch, dass es dir noch wichtiger ist, dass dein iPhone gut funktioniert.", bild = R.drawable.haus7, anleitungPdf = "", kategorie = "", unterkategorie = "Gehäusekomponenten"))





        // Kategorie Anleitung

        sortiment.add(Artikel(artikelBezeichnung = "Display Anleitung", preis = 0.0, artikelBeschreibung = "Wenn das Display deines iPhone 11 zerbrochen ist, kein Bild beim Einschalten zeigt oder nicht auf Berührung reagiert, kannst du mit Hilfe dieser Anleitung dein iPhone wieder zum Laufen bringen.", bild = R.drawable.anleitungdisplay, anleitungPdf = "", kategorie = "Anleitung", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Akku Anleitung", preis = 0.0, artikelBeschreibung = "Mit Hilfe dieser Anleitung kannst du den Akku ersetzen, um wieder eine normale Ladefähigkeit zu erhalten.", bild = R.drawable.anleitungakku, anleitungPdf = "", kategorie = "Anleitung", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Lautsprecher Anleitung", preis = 0.0, artikelBeschreibung = "Diese Anleitung zeigt dir, wie du den Hauptlautsprecher im iPhone 11 austauschen kannst.", bild = R.drawable.anleitungkamera, anleitungPdf = "", kategorie = "Anleitung", unterkategorie = ""))
        sortiment.add(Artikel(artikelBezeichnung = "Kamera Anleitung", preis = 0.0, artikelBeschreibung = "Nutze diese Anleitung, um die Rückkameras deines iPhone 11 zu ersetzen. Die Einheit besteht aus einer Weitwinkelkamera und einer Ultraweitwinkelkamera, die als Einheit ausgetauscht werden.", bild = R.drawable.anleitunglautsprecher, anleitungPdf = "", kategorie = "Anleitung", unterkategorie = ""))




        if (database.artikelDao.getCount() == 0){
            try {
                database.artikelDao.insertAll(sortiment)
            } catch (e: Exception){
                Log.e("AppRepository","error insert data into Database $e")
            }
        }
    }

}