package com.example.acromineapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acromineapp.data.model.Lf
import com.example.acromineapp.databinding.InformationItemBinding

class InformationAdapter(
    private val infoList: MutableList<Lf> = mutableListOf(),
) : RecyclerView.Adapter<InformationAdapter.ViewHolder>() {

    fun updateInfoAdapter(newSearch: List<Lf>) {
        infoList.clear()
        infoList.addAll(newSearch)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val items: InformationItemBinding) :
        RecyclerView.ViewHolder(items.root) {
        fun fillInfo(infoResponse: Lf) {
            items.tvMeaning.text = infoResponse.lf
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        InformationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = infoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fillInfo(infoList[position])
    }
}