package com.example.abschlussaufgabe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.db.Artikel
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


        /**
         * Klick Button für Erhöhung des Produktes
         */
        holder.binding.ivPluscircle.setOnClickListener {
            expandItemFromShoppingCart(warenkorb, holder)
        }

        /**
         * Klick Button für Reduzierung des Produktes
         */
        holder.binding.ivMinuscircle.setOnClickListener {
            reduceItemFromShoppingCard(warenkorb, holder)
        }

        /**
         * Klick Button für Löschen des Produktes
         */
        holder.binding.ivTrash.setOnClickListener {
            deleteItemFromShoppingcart(warenkorb, position)
        }
        
    }


    /**
     * Diese Funktion erhöht die menge des Produktes im Warenkorb
     */
    private fun expandItemFromShoppingCart(warenkorb: Artikel, holder: ItemViewHolder) {
        viewmodel.updatePrices(warenkorb.preis)
        var menge: Int = holder.binding.tvMenge.text.toString().toInt()
        menge++
        holder.binding.tvMenge.setText((menge.toString()))
        val artikel = warenkorb
        artikel.warenkorbMenge++
        viewmodel.updateArtikel(artikel)
    }


    /**
     * Diese Funktion Reduziert über das Minus Icon die Menge vom Produkt
     */
    private fun reduceItemFromShoppingCard(warenkorb: Artikel, holder: ItemViewHolder) {
        if (warenkorb.warenkorbMenge > 1) {
            viewmodel.updatePrices(-warenkorb.preis)
            var reduzieren: Int = holder.binding.tvMenge.text.toString().toInt()
            reduzieren--
            holder.binding.tvMenge.setText(reduzieren.toString())
            val artikel = warenkorb
            artikel.warenkorbMenge--
            viewmodel.updateArtikel(artikel)
        }
    }


    /**
     * Diese Funktion löscht ein Produkt in der Recyclerview wenn man auf das Trash Item klickt
     */
    private fun deleteItemFromShoppingcart(warenkorb: Artikel, position: Int) {
        viewmodel.updatePrices(-warenkorb.preis * warenkorb.warenkorbMenge)
        val artikel = warenkorb
        artikel.warenkorbMenge = 0
        viewmodel.updateArtikel(artikel)
        datasource.removeAt(position)
        notifyItemRemoved(position)
    }

}