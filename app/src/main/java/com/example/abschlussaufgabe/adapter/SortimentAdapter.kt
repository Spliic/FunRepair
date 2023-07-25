package com.example.abschlussaufgabe.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlussaufgabe.data.datamodel.Artikel
import com.example.abschlussaufgabe.databinding.SortimentItemListBinding
import com.example.abschlussaufgabe.ui.SortimentFragmentDirections


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

        /**
         * Hier werden die Argumente gebindet und gesetzt.
         */
        holder.binding.ivItem.setImageResource(sortiment.bild)
        holder.binding.tvDescription.setText(sortiment.artikelBeschreibung)
        holder.binding.tvProductname.setText(sortiment.artikelBezeichnung)
        holder.binding.tvPrice.setText(sortiment.preis.toString()+ " €")


        /**
         * Hier wird von der CardView navigiert zum DetailFragment mit den Argumenten die benötigt werden.
         */
        holder.binding.mcvItem.setOnClickListener {
            holder.itemView.findNavController().navigate(SortimentFragmentDirections.actionSortimentFragmentToDetailFragment(sortiment.artikelBezeichnung,sortiment.preis.toFloat(),sortiment.artikelBeschreibung,sortiment.bild))
        }
    }



}