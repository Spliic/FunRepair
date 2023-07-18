package com.example.abschlussaufgabe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.WerkzeugItemListBinding
import com.example.abschlussaufgabe.ui.HomeFragmentDirections
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class WerkzeugAdapter(
    private val datasource: List<Artikel>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<WerkzeugAdapter.ItemViewHolder>() {


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

        val werkzeug = datasource[position]

        holder.binding.ivItem.setImageResource(werkzeug.bild)
        holder.binding.tvProductname.setText(werkzeug.artikelBezeichnung)
        holder.binding.tvDescription.setText(werkzeug.artikelBeschreibung)


        holder.binding.mcvItem.setOnClickListener {

            viewModel.setSortimentList(getCategoryList(werkzeug))
            holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSortimentFragment(werkzeug.artikelBezeichnung))
        }
    }

    private fun getCategoryList(werkzeug: Artikel): List<Artikel>{
        val kategorieList = mutableListOf<Artikel>()

        for (i in datasource){
            if (i.unterkategorie == werkzeug.unterkategorie){
                kategorieList.add(i)
            }
        }
        Log.e("WerkzeugAdapter","Error loading List ${kategorieList.size}")
        return kategorieList
    }

}