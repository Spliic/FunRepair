package com.example.abschlussaufgabe

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.abschlussaufgabe.adapter.ViewPagerAdapter
import com.example.abschlussaufgabe.databinding.ActivityMainBinding
import com.example.abschlussaufgabe.ui.OnboardOneFragment
import com.example.abschlussaufgabe.ui.OnboardThreeFragment
import com.example.abschlussaufgabe.ui.OnboardTwoFragment
import com.example.abschlussaufgabe.viewmodel.MainViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView3.id) as NavHostFragment
        navController = navHostFragment.navController

        setupWithNavController(bottomNavigationBar, navController)

    }



}