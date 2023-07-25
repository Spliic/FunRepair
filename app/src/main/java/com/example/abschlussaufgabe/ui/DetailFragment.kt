package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.databinding.FragmentDetailBinding
import com.example.abschlussaufgabe.databinding.FragmentSortimentBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel


class DetailFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding


    private var artikelBezeichnung = ""
    private var preis = 0
    private var artikelBeschreibung = ""
    private var bild = 0


    /**
     * Hier in der OnStart wird die HideNavigation & HideToolbar einblendet
     */
    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artikelBezeichnung = it.getString("artikelbezeichnung").toString()
            preis = it.getInt("preis")
            artikelBeschreibung = it.getString("artikelbeschreibung").toString()
            bild = it.getInt("bild")

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivDetail.setImageResource(bild)
        binding.tvPrice.setText(preis)
        binding.tvDescription.setText(artikelBeschreibung)
        binding.tvProductname.setText(artikelBezeichnung)






    }

}