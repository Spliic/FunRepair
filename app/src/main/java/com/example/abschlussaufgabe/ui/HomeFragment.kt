package com.example.abschlussaufgabe.ui

import android.os.Bundle
import android.util.Log
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


    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)


    }

    override fun onResume() {
        super.onResume()
        Log.e("HomeFragment","dwadada")
        viewModel.hideToolbar(true)
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

        viewModel.ersatzteilList.observe(viewLifecycleOwner){ersatzteilList ->
            binding.rvNewitem.adapter = KategorieAdapter(ersatzteilList,viewModel)

        }


       // TODO: Observen
        binding.cvErsatzteile.setOnClickListener {
            binding.rvNewitem.adapter = KategorieAdapter(viewModel.ersatzteilList.value!!,viewModel)
        }

        binding.cvWerkzeug.setOnClickListener {
            binding.rvNewitem.adapter = KategorieAdapter(viewModel.werkzeugList.value!!,viewModel)
        }

        binding.cvAnleitung.setOnClickListener {
            binding.rvNewitem.adapter = KategorieAdapter(viewModel.anleitungList.value!!,viewModel)
        }

    }


}