package com.example.abschlussaufgabe.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.activityViewModels

import com.example.abschlussaufgabe.databinding.FragmentWerkstattBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel



class WerkstattFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentWerkstattBinding







    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)
        viewModel.setSortimentTitle("Werkstatt")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWerkstattBinding.inflate(inflater,container,false)
       // binding. .getMapAsync(this)
        return binding.root
    }


    /*
    val filteredSortiment = viewModel.completeSortimentList.filter {it. neues atribut > 0 }
        binding.rvSortiment.adapter = WarenkorbAdapter(filteredSortiment)
     */


}

