package com.tauan.desafiostone.presenter.ui.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tauan.desafiostone.databinding.ItemCardLayoutBinding
import com.tauan.desafiostone.model.Item
import com.tauan.desafiostone.presenter.viewmodel.CartViewModel

class ItemAdapter(private val lifecycleOwner: LifecycleOwner, private val adapterClick: AdapterClick, private val viewModel: CartViewModel) : RecyclerView.Adapter<ItemViewHolder>() {

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
        val binding  = ItemCardLayoutBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
        )

        binding.lifecycleOwner = lifecycleOwner
        return ItemViewHolder(binding, viewModel, adapterClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}