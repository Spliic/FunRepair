package com.example.abschlussaufgabe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
import com.example.abschlussaufgabe.model.MainViewModel

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
        val fragmentParams = binding.fragmentContainerView3.layoutParams as ViewGroup.MarginLayoutParams

        /**
         * Hier setzten wir vom Fragment die Margins
         */
        fragmentParams.setMargins(0,0,0,0)

        /**
         *  Hier Observen wir vom Viewmodel und setzten die Sichtbarkeit und die Magins des Fragments.
         */
        viewModel.hideToolbar.observeForever {
            if (it == false) {
                binding.cvToolbar.visibility = View.VISIBLE
                //setFragmentMargins(fragmentParams)


            }
        }

        viewModel.hideNavigation.observeForever {
            if (it == false) {
                binding.bottomNavigation.visibility = View.VISIBLE
                //setFragmentMargins(fragmentParams)

            }
        }

    }


    private fun setFragmentMargins(fragmentParams: ViewGroup.MarginLayoutParams){
        if (viewModel.hideToolbar.value == true && viewModel.hideNavigation.value == false){
            fragmentParams.setMargins(0,0,0,80)
        } else {
            fragmentParams.setMargins(0,80,0,80)
        }
    }

}