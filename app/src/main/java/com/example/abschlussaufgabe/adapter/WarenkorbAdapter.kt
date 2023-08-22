package com.example.abschlussaufgabe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.SortimentItemListBinding
import com.example.abschlussaufgabe.databinding.WarenkorbListItemBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class WarenkorbAdapter(
    private val datasource: MutableList<Artikel>,
    private val viewmodel: MainViewModel
): RecyclerView.Adapter<WarenkorbAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: WarenkorbListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WarenkorbAdapter.ItemViewHolder {
        val binding =
            WarenkorbListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datasource.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val warenkorb = datasource[position]


        holder.binding.ivItem.setImageResource(warenkorb.bild)
        holder.binding.tvProductname.setText(warenkorb.artikelBezeichnung)
        holder.binding.tvPrice.setText(warenkorb.preis.toString() + "€")
        holder.binding.tvMenge.setText(warenkorb.warenkorbMenge.toString())
        holder.binding.ivPluscircle.setOnClickListener {
            viewmodel.updatePrices(warenkorb.preis)
            var menge: Int = holder.binding.tvMenge.text.toString().toInt()
            menge++
            holder.binding.tvMenge.setText((menge.toString()))
            val artikel = warenkorb
            artikel.warenkorbMenge++
            viewmodel.updateArtikel(artikel)
        }

        holder.binding.ivMinuscircle.setOnClickListener {
            viewmodel.updatePrices(- warenkorb.preis)
            var reduzieren: Int = holder.binding.tvMenge.text.toString().toInt()
            reduzieren--
            holder.binding.tvMenge.setText(reduzieren.toString())
            val artikel = warenkorb
            artikel.warenkorbMenge--
            viewmodel.updateArtikel(artikel)
        }

        holder.binding.ivTrash.setOnClickListener {
            viewmodel.updatePrices(- warenkorb.preis * warenkorb.warenkorbMenge)
            val artikel = warenkorb
            artikel.warenkorbMenge = 0
            viewmodel.updateArtikel(artikel)
            datasource.removeAt(position)
            notifyItemRemoved(position)
        }

    }

}