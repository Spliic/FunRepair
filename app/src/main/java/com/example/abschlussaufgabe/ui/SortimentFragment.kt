package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.adapter.SortimentAdapter
import com.example.abschlussaufgabe.databinding.FragmentSortimentBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class SortimentFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSortimentBinding


    /**
     * Hier in der OnStart wird die HideNavigation & HideToolbar einblendet
     */
    override fun onStart() {
        super.onStart()
        viewModel.hideNavigation(false)
        viewModel.hideToolbar(false)

    }


    private var unterkategorie: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /**
         * Dieser Codeausschnitt in Kotlin überprüft, ob arguments einen Wert hat, und weist den String-Wert mit dem Schlüssel "unterkategorie" der Variablen unterkategorie zu
         */
        arguments?.let{
            unterkategorie = it.getString("unterkategorie")
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sortiment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Dieser Code filtert eine Liste basierend auf einer bestimmten Bedingung und zeigt das Ergebnis in einem RecyclerView an.
         */
        val filteredSortiment = viewModel.completeSortimentList.filter {it.unterkategorie == unterkategorie}
        binding.rvSortiment.adapter = SortimentAdapter(filteredSortiment)
    }
}