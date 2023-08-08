package com.example.abschlussaufgabe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
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


        /**
         * Hier navigieren wir an die Stelle zurück von der Wir gekommen sind über den Back Button
         */
        binding.ivBackIcon.setOnClickListener {
            navController.navigateUp()
        }

        /**
         * Hier Navigieren wir in der Botton Navigation Bar
         */
        val bottomNavigationBar = binding.bottomNavigation
        bottomNavigationBar.setOnItemSelectedListener {
            if (it.itemId == R.id.homeFragment){
                navController.navigate(R.id.homeFragment)
                 true
            }
            false
        }

        /**
         * Code verwendet Navigation Component für App-Navigation. NavHostFragment erhält NavController aus supportFragmentManager. setupWithNavController verbindet mit unterer Navigationsleiste.
         */
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView3.id) as NavHostFragment
        navController = navHostFragment.navController

        setupWithNavController(bottomNavigationBar, navController)

    }



}