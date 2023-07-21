package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.databinding.FragmentSucheBinding
import com.example.abschlussaufgabe.databinding.FragmentWarenkorbBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel


class WarenkorbFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentWarenkorbBinding


    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warenkorb, container, false)
    }


}