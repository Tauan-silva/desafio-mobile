package com.tauan.desafiostone.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tauan.desafiostone.databinding.ItemCardLayoutBinding
import com.tauan.desafiostone.domain.model.Product

class ItemAdapter(private val adapterClick: AdapterClick) : RecyclerView.Adapter<ItemViewHolder>() {

    var products = emptyList<Product>()
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

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)

        with(holder.binding){
            btnAdd.setOnClickListener {
                labelCount.text = item.quantity.toString()
                adapterClick.add(item)
                btnAdd.visibility = View.GONE
                elegantNumber.visibility = View.VISIBLE
            }

            btnAddMore.setOnClickListener {
                adapterClick.add(item)
                item.quantity++
                labelCount.text = item.quantity.toString()
            }

            btnRemove.setOnClickListener {
                adapterClick.remove(item)
                item.quantity--

                if (item.quantity < 0 ) {
                    item.quantity = 0
                }

                labelCount.text = item.quantity.toString()

                if (item.quantity == 0){
                    item.quantity = 1
                    btnAdd.visibility = View.VISIBLE
                    elegantNumber.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int = products.size
}