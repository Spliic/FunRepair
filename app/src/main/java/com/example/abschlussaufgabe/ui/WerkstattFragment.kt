package com.example.abschlussaufgabe.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.R

import com.example.abschlussaufgabe.databinding.FragmentWerkstattBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class WerkstattFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentWerkstattBinding

    private lateinit var googleMap: GoogleMap





    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)
        viewModel.setSortimentTitle("Werkstatt")
        binding.map.onStart()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWerkstattBinding.inflate(inflater,container,false)
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync{googleMap ->
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(53.65432,10.09070),16f))
            googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(53.65432,10.09070))
                    .title("MyRepair & MyMobile Alstertal Einkaufszentrum 1.OG")
            )
            googleMap.uiSettings.isZoomControlsEnabled = true

        }
        return binding.root
    }





    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onMapReady(p0: GoogleMap) {


    }


    /*
    val filteredSortiment = viewModel.completeSortimentList.filter {it. neues atribut > 0 }
        binding.rvSortiment.adapter = WarenkorbAdapter(filteredSortiment)
     */


}

