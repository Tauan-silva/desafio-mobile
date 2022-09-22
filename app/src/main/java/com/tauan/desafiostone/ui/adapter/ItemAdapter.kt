package com.tauan.desafiostone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tauan.desafiostone.databinding.ItemCardLayoutBinding
import com.tauan.desafiostone.model.Item

class ItemAdapter(private val adapterClick: AdapterClick) : RecyclerView.Adapter<ItemViewHolder>() {

    var items = emptyList<Item>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ItemListCallback(
                    field, value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), adapterClick
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}