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
import com.example.abschlussaufgabe.adapter.ViewPagerAdapter
import com.example.abschlussaufgabe.databinding.FragmentOnboardOneBinding

class OnboardOneFragment : Fragment() {

    private lateinit var binding: FragmentOnboardOneBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_onboard_one,container,false)

        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(OnboardOneFragmentDirections.actionOnboardOneFragmentToOnboardTwoFragment())
        },3000)

        return binding.root
    }



}