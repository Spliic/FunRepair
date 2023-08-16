package com.example.abschlussaufgabe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.SortimentItemListBinding
import com.example.abschlussaufgabe.databinding.WarenkorbListItemBinding
import com.example.abschlussaufgabe.viewmodel.MainViewModel

class WarenkorbAdapter(
    private val datasource: List<Artikel>,
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

        holder.binding.ivPluscircle.setOnClickListener {
            viewmodel.updatePrices(warenkorb.preis)
            var menge: Int = holder.binding.tvMenge.text.toString().toInt()
            menge++
            holder.binding.tvMenge.setText((menge.toString()))
        }

        holder.binding.ivMinuscircle.setOnClickListener {
            viewmodel.updatePrices(- warenkorb.preis)
            var reduzieren: Int = holder.binding.tvMenge.text.toString().toInt()
            reduzieren--
            holder.binding.tvMenge.setText(reduzieren.toString())
        }

        holder.binding.ivTrash.setOnClickListener {

        }





    }

    fun increaseNumber(){

    }

}