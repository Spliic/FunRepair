package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.adapter.WarenkorbAdapter
import com.example.abschlussaufgabe.data.database.getDatabase
import com.example.abschlussaufgabe.data.datamodel.db.Artikel
import com.example.abschlussaufgabe.databinding.FragmentZahlenBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel


class ZahlenFragment : Fragment(R.layout.fragment_zahlen) {



    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentZahlenBinding



    override fun onStart() {
        super.onStart()
        viewModel.setSortimentTitle("Warenkorb")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_zahlen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonZahlen.setOnClickListener {
            Toast.makeText(context,"Zahlung erfolgreich! Vielen dank für Ihren Einkauf",Toast.LENGTH_LONG).show()
            findNavController().navigate(ZahlenFragmentDirections.actionZahlenFragmentToWarenkorbFragment())
        }
    }

}