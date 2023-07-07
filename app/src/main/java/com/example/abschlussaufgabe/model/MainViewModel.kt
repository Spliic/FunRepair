package com.example.abschlussaufgabe.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussaufgabe.MainActivity

class MainViewModel(application: Application): AndroidViewModel(application) {

    val mainActivity = MainActivity()

    private val _hideToolbarAndNavigation = MutableLiveData(false)
    val hideToolbarAndNavigation: LiveData<Boolean>
        get() = _hideToolbarAndNavigation



}