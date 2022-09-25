package com.tauan.desafiostone.presenter.ui.adapter

import com.tauan.desafiostone.model.Item

interface AdapterClick {
    fun add(item: Item)
    fun remove(item: Item)
}