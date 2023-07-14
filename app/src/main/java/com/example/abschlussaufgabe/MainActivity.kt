package com.example.abschlussaufgabe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        /**
         * Hier entfernen wir die Sichtbarkeit von der Toolbar und Navibar
         */
        binding.cvToolbar.visibility = View.GONE
        binding.bottomNavigation.visibility = View.GONE

        /**
         *  Hier Observen wir vom Viewmodel und setzten die Sichtbarkeit und die Magins des Fragments.
         */
        viewModel.hideToolbar.observeForever {
            if (it == false) {
                binding.cvToolbar.visibility = View.VISIBLE
            }
        }

        viewModel.hideNavigation.observeForever {
            if (it == false) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }


    }


}