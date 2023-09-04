package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.databinding.FragmentAdressenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AdressenFragment : Fragment() {

    private lateinit var binding: FragmentAdressenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_adressen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            if (validateInputs()) {
                findNavController().navigate(AdressenFragmentDirections.actionAdressenFragmentToZahlenFragment())
            }
        }
    }

    private fun validateInputs(): Boolean {
        val name = binding.textInputName.editText?.text.toString()
        val telefonnummer = binding.textInputNr.editText?.text.toString()
        val straße = binding.textInputStraE.editText?.text.toString()
        val hausnummer = binding.textInputNr.editText?.text.toString()
        val plz = binding.textInputPlz.editText?.text.toString()


        if (name.isEmpty()) {
            binding.textInputName.error = "Vollständiger Name darf nicht Leer sein"
            return false
        } else {
            binding.textInputName.error = null
        }

        if (telefonnummer.isEmpty()){
            binding.textInputNr.error = "Telefonnummer darf nicht leer sein"
            return false
        } else {
            binding.textInputNr.error = null
        }

        if (straße.isEmpty()){
            binding.textInputStraE.error = "Straße darf nicht leer sein"
            return false
        } else {
            binding.textInputStraE.error = null
        }

        if (hausnummer.isEmpty()) {
            binding.text.error = "Hausnummer darf nicht leer sein"
            return false
        } else {
            binding.text.error = null
        }

        if (plz.isEmpty()){
            binding.textInputPlz.error = "Postleitzahl darf nicht leer sein"
            return false
        } else {
            binding.textInputPlz.error = null
        }
        return true
    }


}