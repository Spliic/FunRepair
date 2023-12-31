package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.abschlussaufgabe.adapter.SortimentAdapter
import com.example.abschlussaufgabe.data.datamodel.db.Artikel
import com.example.abschlussaufgabe.databinding.FragmentSucheBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel


class SucheFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSucheBinding


    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)
        viewModel.setSortimentTitle("Suche")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSucheBinding.inflate(inflater,container,false)
        return binding.root
    }


}