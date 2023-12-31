package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.databinding.FragmentProfilBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfilFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentProfilBinding

    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)
        viewModel.setSortimentTitle("Profil")
    }

    override fun onResume() {
        super.onResume()
        viewModel.hideToolbar(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvAbmelden.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(ProfilFragmentDirections.actionProfilFragmentToLoginFragment())
        }

        /**
         * Hier wird die aktuelle Temperatur angezeigt über die API
         */
        binding.btnWetter.setOnClickListener {
            viewModel.currentWetter.observe(viewLifecycleOwner) {
                binding.wetterApixml.text = it.current.temperature.toString() + "° C"
            }
        }

    }

}