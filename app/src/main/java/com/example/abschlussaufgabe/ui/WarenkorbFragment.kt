package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.adapter.SortimentAdapter
import com.example.abschlussaufgabe.adapter.WarenkorbAdapter
import com.example.abschlussaufgabe.data.datamodel.Artikel
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
        viewModel.setSortimentTitle("Warenkorb")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_warenkorb,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val filteredSortiment = viewModel.completeSortimentList.filter {it.warenkorbMenge > 0}
        binding.rvSortiment.adapter = WarenkorbAdapter(filteredSortiment,viewModel)

        var sum = 0.0


        for (filter in filteredSortiment){
            for (i in 1..filter.warenkorbMenge){
                sum += filter.preis
            }
        }
        viewModel.initPrices(sum)
        viewModel.allPrices.observe(viewLifecycleOwner){
            binding.tvPriceAll.setText(String.format("%.2f", it) + "€")
        }


    }




}