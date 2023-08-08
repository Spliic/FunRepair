package com.example.abschlussaufgabe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.WerkzeugItemListBinding
import com.example.abschlussaufgabe.ui.HomeFragmentDirections
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class KategorieAdapter(
    private val datasource: List<Artikel>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<KategorieAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val binding: WerkzeugItemListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            WerkzeugItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val kategorie = datasource[position]

        holder.binding.ivItem.setImageResource(kategorie.bild)
        holder.binding.tvProductname.setText(kategorie.artikelBezeichnung)
        holder.binding.tvDescription.setText(kategorie.artikelBeschreibung)


        holder.binding.mcvItem.setOnClickListener {

            viewModel.setSortimentList(getCategoryList(kategorie))
            holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSortimentFragment(kategorie.artikelBezeichnung))

        }
    }


    /**
     * Die Funktion getCategoryList erstellt eine Liste von Artikel-Objekten, die zur gleichen unterkategorie wie das gegebene werkzeug gehören.
     */
    private fun getCategoryList(werkzeug: Artikel): List<Artikel>{
        val kategorieList = mutableListOf<Artikel>()

        for (i in datasource){
            if (i.unterkategorie == werkzeug.unterkategorie){
                kategorieList.add(i)
            }
        }
        return kategorieList
    }

}