package com.example.abschlussaufgabe.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.databinding.FragmentOnboardOneBinding
import com.example.abschlussaufgabe.databinding.FragmentOnboardTwoBinding


class OnboardTwoFragment : Fragment() {


    private lateinit var binding: FragmentOnboardTwoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_onboard_two,container,false)

        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(OnboardTwoFragmentDirections.actionOnboardTwoFragmentToOnboardThreeFragment())
        },2000)
        return binding.root
    }
}

