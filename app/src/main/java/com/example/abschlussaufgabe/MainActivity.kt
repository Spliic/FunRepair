package com.example.abschlussaufgabe

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
import com.example.abschlussaufgabe.model.MainViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModel = MainViewModel(Application())
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        /**
         * Hier entfernen wir die Sichtbarkeit von der Toolbar und Navibar
         */
        binding.cvToolbar.visibility = View.GONE
        binding.cvNavibar.visibility = View.GONE
        val fragmentParams = binding.fragmentContainerView3.layoutParams as ViewGroup.MarginLayoutParams

        /**
         * Hier setzten wir vom Fragment die Margins
         */
        fragmentParams.setMargins(0,0,0,0)

        /**
         *  Hier Observen wir vom Viewmodel und setzten die Sichtbarkeit und die Magins des Fragments.
         */
        viewModel.hideToolbarAndNavigation.observeForever {
            if (it == false) {
                binding.cvToolbar.visibility = View.VISIBLE
                binding.cvNavibar.visibility = View.VISIBLE
                fragmentParams.setMargins(0,80,0,80)
            }
        }
    }

}