package com.example.abschlussaufgabe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.WerkzeugItemListBinding

class WerkzeugAdapter(
    private val datasource: List<Artikel>
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
    }

}