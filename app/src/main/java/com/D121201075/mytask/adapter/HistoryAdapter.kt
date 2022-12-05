package com.D121201075.mytask.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201075.mytask.R
import com.D121201075.mytask.fragments.HistoryFragmentDirections
import com.D121201075.mytask.model.Tugas
import com.D121201075.mytask.viewmodel.TugasViewModel


class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.TugasViewHolder>() {
    private var context: Context? = null
    private var tugasList = emptyList<Tugas>()
    private lateinit var tugasViewModel :TugasViewModel

    class TugasViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val judul:TextView = itemView.findViewById(R.id.judul_tugas)
        val isi:TextView = itemView.findViewById(R.id.isi_tugas)
        val kategori:TextView = itemView.findViewById(R.id.kategori)
        val moreButton:ImageButton = itemView.findViewById(R.id.popup_tugas)
        val klikToDetail:CardView = itemView.findViewById(R.id.klikTugas)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        return TugasViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_tugas,parent,false))
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val currentItem = tugasList[position]

        holder.judul.text = currentItem.nama_tugas
        holder.isi.text = currentItem.isi_tugas
        holder.kategori.text = currentItem.kategori_tugas
        when (currentItem.kategori_tugas) {
            context!!.resources.getStringArray(R.array.kategoriArray)[0] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.penting_mendesak))
            }
            context!!.resources.getStringArray(R.array.kategoriArray)[1] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.tidak_penting_mendesak))
            }
            context!!.resources.getStringArray(R.array.kategoriArray)[2] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.penting_tidak_mendesak))
            }
            context!!.resources.getStringArray(R.array.kategoriArray)[3] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.tidak_penting_tidak_mendesak))
            }
        }
        holder.moreButton.visibility = View.GONE
        holder.klikToDetail.setOnClickListener {
            val action = HistoryFragmentDirections.actionHistoryFragmentToDetailTugasFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return tugasList.size
    }

    fun setData(tugas:List<Tugas>){
        this.tugasList = tugas
        notifyDataSetChanged()
    }



}