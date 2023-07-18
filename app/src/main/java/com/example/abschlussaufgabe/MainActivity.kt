package com.example.abschlussaufgabe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
import com.example.abschlussaufgabe.ui.HomeFragment
import com.example.abschlussaufgabe.ui.ProfilFragment
import com.example.abschlussaufgabe.ui.SucheFragment
import com.example.abschlussaufgabe.ui.WarenkorbFragment
import com.example.abschlussaufgabe.ui.WerkstattFragment
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
            } else {
                binding.cvToolbar.visibility = View.GONE
            }
        }
        viewModel.hideNavigation.observeForever {
            if (it == false) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.NavHome ->{
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.NavSuche ->{
                    replaceFragment(SucheFragment())
                    true
                }
                R.id.NavWarenkorb ->{
                    replaceFragment(WarenkorbFragment())
                    true
                }
                R.id.NavPerson ->{
                    replaceFragment(ProfilFragment())
                    true
                }
                R.id.NavWerkstatt ->{
                    replaceFragment(WerkstattFragment())
                    true
                }
                else -> {
                    true
                }
            }
        }
    }


    /**
     * Ersetzt ein Fragment mit einem gegebene Fragment
     * @param fragment ist das Fragment das angezeigt werden soll.
     */
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView3,fragment)
            .commit()
    }

}