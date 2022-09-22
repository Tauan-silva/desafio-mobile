package com.tauan.desafiostone.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tauan.desafiostone.model.Item
import com.tauan.desafiostone.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ItemRepository @Inject constructor(private val apiService: ApiService) {
    private val itemLiveData = MutableLiveData<List<Item>?>()

    suspend fun getItems(): MutableLiveData<List<Item>?> {
        CoroutineScope(Dispatchers.IO).launch {
            apiService.getItems().enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    if (response.isSuccessful) {
                        itemLiveData.postValue(response.body())
                    } else {
                        itemLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    Log.e(this.javaClass.toString(), "onFailure: ", t.cause)
                    itemLiveData.postValue(null)
                }

            })
        }
        withContext(Dispatchers.Main) {
            return@withContext itemLiveData
        }

        return itemLiveData
    }
}