package com.example.abschlussaufgabe.viewmodel

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.data.db.AppRepository
import com.example.abschlussaufgabe.data.db.SortimentDatabase

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AppRepository(SortimentDatabase.getDatabase(application))


    private val _fragmentManager = MutableLiveData<FragmentManager>()
    val fragmentManager:LiveData<FragmentManager>
        get() = _fragmentManager


    fun setFragmentManager(fragmentManager : FragmentManager){
        _fragmentManager.value = fragmentManager

    }


    /**
     * Setze die Livedata für den titel
     */
    private val _sortimentTitel = MutableLiveData<String>()
    val sortimentTitel: LiveData<String>
        get() = _sortimentTitel


    fun setSortimentTitle(titel: String){
        _sortimentTitel.value = titel
    }


    /**
     * Setze LiveData für ErsatzteilListe
     */
    private val _ersatzteilList = MutableLiveData<List<Artikel>>()
    val ersatzteilList : LiveData<List<Artikel>>
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



    private val _sortimentList = MutableLiveData<List<Artikel>>()
    val sortimentList: LiveData<List<Artikel>>
        get() = _sortimentList

    val completeSortimentList = repository.completeSortimentList

    fun setSortimentList(liste: List<Artikel>){
        _sortimentList.value = liste
    }



    init {
        repository.loadData()
        _anleitungList.value = repository.anleitungList.value
        _werkzeugList.value = repository.werkzeugList.value
        _ersatzteilList.value = repository.ersatzteilList.value

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



    /*private val _currentFragment = MutableLiveData<String>()
    val currentFragment: LiveData<String>
        get() = _currentFragment

    fun setCurrentFragment(currentFragment: String){
        _currentFragment.value = currentFragment
    }

     */

    /**
     * Set live data value for _hideToolbar
     */
    fun hideToolbar(hide: Boolean){
        _hideToolbar.value = hide
    }

    /**
     * Set Live data value for _hideNavigation
     */
    fun hideNavigation(hide:Boolean){
        _hideNavigation.value = hide
    }




}