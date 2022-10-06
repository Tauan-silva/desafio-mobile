package com.tauan.desafiostone.presentation.ui.adapter

import com.tauan.desafiostone.domain.model.Product

class ItemListCallback(private val oldList: List<Product>, private val newList: List<Product>): DiffCallback<Product>(oldList, newList) {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true
}