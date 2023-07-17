package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.adapter.WerkzeugAdapter
import com.example.abschlussaufgabe.databinding.FragmentHomeBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding


    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)

    }


    /**
     * die OnCreateView zeigt das UI Element an
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }

        binding.rvNewitem.adapter = WerkzeugAdapter(viewModel.ersatzteilList.value!!)


        binding.cvErsatzteile.setOnClickListener {
            binding.rvNewitem.adapter = WerkzeugAdapter(viewModel.ersatzteilList.value!!)
        }

        binding.cvWerkzeug.setOnClickListener {
            binding.rvNewitem.adapter = WerkzeugAdapter(viewModel.werkzeugList.value!!)
        }

        binding.cvAnleitung.setOnClickListener {
            binding.rvNewitem.adapter = WerkzeugAdapter(viewModel.anleitungList.value!!)
        }







    }


}