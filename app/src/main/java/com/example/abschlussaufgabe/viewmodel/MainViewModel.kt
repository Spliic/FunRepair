package com.example.abschlussaufgabe.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlussaufgabe.data.apiServices.WetterApi
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.data.datamodel.CurrentWeather
import com.example.abschlussaufgabe.data.db.AppRepository
import com.example.abschlussaufgabe.data.db.SortimentDatabase
import com.example.abschlussaufgabe.data.db.getDatabase
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = AppRepository(getDatabase(application), WetterApi)



    fun initPrices(price: Double) {
        _allPrices.value = price
    }


    fun updatePrices(price: Double) {
        _allPrices.value = _allPrices.value!! + price
    }

    private val _allPrices = MutableLiveData(0.0)
    val allPrices: LiveData<Double>
        get() = _allPrices


    private val _currentWetter = MutableLiveData<CurrentWeather>()
    val currentWetter: LiveData<CurrentWeather>
        get() = _currentWetter

    /**
     * Setze die Livedata für den titel
     */
    private val _sortimentTitel = MutableLiveData<String>()
    val sortimentTitel: LiveData<String>
        get() = _sortimentTitel

    fun setSortimentTitle(titel: String) {
        _sortimentTitel.value = titel
    }


    /**
     * Setze LiveData für ErsatzteilListe
     */
    private val _ersatzteilList = MutableLiveData<List<Artikel>>()
    val ersatzteilList: LiveData<List<Artikel>>
        get() = _ersatzteilList


    /**
     * Setze LiveData für WerkzeugListe
     */
    private val _werkzeugList = MutableLiveData<List<Artikel>>()
    val werkzeugList: LiveData<List<Artikel>>
        get() = _werkzeugList


    /**
     * Setze LiveData für AnleitungsListe
     */
    private val _anleitungList = MutableLiveData<List<Artikel>>()
    val anleitungList: LiveData<List<Artikel>>
        get() = _anleitungList


    /**
     * Hier wird eine LiveData-Liste von Artikel-Objekten verwaltet:
    _sortimentList ist eine private LiveData-Liste.
    sortimentList ist eine öffentliche LiveData-Liste, die auf _sortimentList verweist.
    completeSortimentList kommt direkt vom Repository.
    setSortimentList aktualisiert _sortimentList mit einer neuen Liste.
     */

    private val _sortimentList = MutableLiveData<List<Artikel>>()
    val sortimentList: LiveData<List<Artikel>>
        get() = _sortimentList

    val completeSortimentList = repository.completeSortimentList

    fun setSortimentList(liste: List<Artikel>) {
        _sortimentList.value = liste
    }


    fun updateArtikel(artikel: Artikel) {
        viewModelScope.launch {
            repository.editArtikel(artikel)
        }
    }


    /**
     * Daten aus Repository werden in LiveData-Variablen initialisiert.
     */
    init {
        getWetter()
        repository.loadData()
        _anleitungList.value = repository.anleitungList.value
        _werkzeugList.value = repository.werkzeugList.value
        _ersatzteilList.value = repository.ersatzteilList.value
        _currentWetter.value = repository.currentWetter.value
    }

    /**
     * Live Data to hide toolbar
     */
    private val _hideToolbar = MutableLiveData(true)
    val hideToolbar: LiveData<Boolean>
        get() = _hideToolbar

    /**
     * Live Data to hide for Navigation
     */
    private val _hideNavigation = MutableLiveData(true)
    val hideNavigation: LiveData<Boolean>
        get() = _hideNavigation


    /**
     * Set live data value for _hideToolbar
     */
    fun hideToolbar(hide: Boolean) {
        _hideToolbar.value = hide
    }

    /**
     * Set Live data value for _hideNavigation
     */
    fun hideNavigation(hide: Boolean) {
        _hideNavigation.value = hide
    }

    fun getWetter() {
        viewModelScope.launch {
            repository.getWetter()
            _currentWetter.postValue(repository.currentWetter.value)
        }
    }

}