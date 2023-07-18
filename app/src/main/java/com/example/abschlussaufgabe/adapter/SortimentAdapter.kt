package com.example.abschlussaufgabe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.SortimentItemListBinding
import com.example.abschlussaufgabe.databinding.WerkzeugItemListBinding
import com.example.abschlussaufgabe.ui.HomeFragmentDirections
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class SortimentAdapter(
    private val datasource: List<Artikel>
): RecyclerView.Adapter<SortimentAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding:SortimentItemListBinding) :
            RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortimentAdapter.ItemViewHolder {
        val binding =
            SortimentItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return datasource.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val sortiment = datasource[position]


        holder.binding.ivItem.setImageResource(sortiment.bild)
        holder.binding.tvDescription.setText(sortiment.artikelBeschreibung)
        holder.binding.tvProductname.setText(sortiment.artikelBezeichnung)
        holder.binding.tvPrice.setText(sortiment.preis.toString())
    }



}