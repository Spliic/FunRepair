package com.example.abschlussaufgabe.model

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussaufgabe.MainActivity

class MainViewModel(application: Application): AndroidViewModel(application) {

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