package com.tauan.desafiostone.presenter.ui.adapter

import com.tauan.desafiostone.model.Item

class ItemListCallback(private val oldList: List<Item>, private val newList: List<Item>): DiffCallback<Item>(oldList, newList) {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true
}