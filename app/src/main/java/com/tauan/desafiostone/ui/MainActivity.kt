package com.tauan.desafiostone.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tauan.desafiostone.databinding.ActivityMainBinding
import com.tauan.desafiostone.model.Item
import com.tauan.desafiostone.ui.adapter.AdapterClick
import com.tauan.desafiostone.ui.adapter.ItemAdapter
import com.tauan.desafiostone.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterClick {

    private lateinit var adapterList: ItemAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterList = ItemAdapter(this)
        getItemList()

    }

    private fun getItemList(){
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getItems()
            withContext(Dispatchers.Main) {
                viewModel.itemLiveData.observe(this@MainActivity) {
                    if (it != null) {
                        adapterList.items = it
                        binding.pgbar.visibility = View.GONE
                        setRecyclerView()
                    }
                }
            }
        }
    }
    private fun setRecyclerView() {
        with(binding.recycler) {
            val layoutManager = GridLayoutManager(this@MainActivity, GridLayoutManager.VERTICAL)
            layoutManager.spanCount = 2
            this.layoutManager = layoutManager
            adapter = adapterList
            setHasFixedSize(true)
            visibility = View.VISIBLE
        }
    }

    override fun onClick(item: Item) {
        Snackbar.make(binding.root, item.title, Snackbar.LENGTH_LONG).show()
    }
}