package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.adapter.KategorieAdapter
import com.example.abschlussaufgabe.databinding.FragmentHomeBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding


    /**
     * hier Blenden wir die Navigationbar aus.
     */
    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
    }


    /**
     * Hier blenden wir die Toolbar wieder aus wenn wir zurück Navigieren im Fragment
     */
    override fun onResume() {
        super.onResume()
        viewModel.hideToolbar(true)
    }

    /**
     * die OnCreateView zeigt das UI Element an
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ersatzteilList.observe(viewLifecycleOwner){ersatzteilList ->
            binding.rvNewitem.adapter = KategorieAdapter(ersatzteilList,viewModel)

        }


        binding.cvErsatzteile.setOnClickListener {
            binding.rvNewitem.adapter = KategorieAdapter(viewModel.ersatzteilList.value!!,viewModel)
            viewModel.setSortimentTitle("Ersatzteile")


        }

        binding.cvWerkzeug.setOnClickListener {
            binding.rvNewitem.adapter = KategorieAdapter(viewModel.werkzeugList.value!!,viewModel)
            viewModel.setSortimentTitle("Werkzeug")
        }

        binding.cvAnleitung.setOnClickListener {
            binding.rvNewitem.adapter = KategorieAdapter(viewModel.anleitungList.value!!,viewModel)
            viewModel.setSortimentTitle("Anleitung")
        }


    }


}