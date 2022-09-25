package com.tauan.desafiostone.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauan.desafiostone.model.Item
import com.tauan.desafiostone.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {

    var itemLiveData = MutableLiveData<List<Item>?>()

    fun getItems(): MutableLiveData<List<Item>?> {
        viewModelScope.launch {
            itemLiveData = itemRepository.getItems()
        }

        return itemLiveData
    }
}