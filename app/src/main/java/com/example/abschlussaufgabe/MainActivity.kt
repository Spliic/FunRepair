package com.example.abschlussaufgabe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
import com.example.abschlussaufgabe.ui.HomeFragment
import com.example.abschlussaufgabe.ui.ProfilFragment
import com.example.abschlussaufgabe.ui.SucheFragment
import com.example.abschlussaufgabe.ui.WarenkorbFragment
import com.example.abschlussaufgabe.ui.WerkstattFragment
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController




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
         * Hier Setzten wir Sobald wir auf das Item Klicken den Neuen Titel!
         */
        viewModel.sortimentTitel.observeForever {
            binding.tvTitle.setText(it)
        }

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


        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView3.id) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationBar = binding.bottomNavigation

        setupWithNavController(bottomNavigationBar,navController)



        /**
         * Hier Navigieren wir in der Botton Navigation Bar
         */
        /*binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeNav ->{
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.sucheNav ->{
                    replaceFragment(SucheFragment())
                    viewModel.setSortimentTitle("Suche")
                    true
                }
                R.id.warenkorbNav ->{
                    replaceFragment(WarenkorbFragment())
                    viewModel.setSortimentTitle("Warenkorb")
                    true
                }
                R.id.profilNav ->{
                    replaceFragment(ProfilFragment())
                    viewModel.setSortimentTitle("Profil")
                    true
                }
                R.id.werkstattNav ->{
                    replaceFragment(WerkstattFragment())
                    viewModel.setSortimentTitle("Werkstatt")
                    true
                }
                else -> {
                    true
                }
            }
        }

         */


    }


    /**
     * Ersetzt ein Fragment mit einem gegebene Fragment
     * @param fragment ist das Fragment das angezeigt werden soll.
     */
    private fun replaceFragment(fragment: Fragment){
        viewModel.setFragmentManager(supportFragmentManager)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView3,fragment)
            .commit()
    }

}