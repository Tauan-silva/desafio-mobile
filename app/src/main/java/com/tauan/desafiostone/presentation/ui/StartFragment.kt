package com.tauan.desafiostone.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tauan.desafiostone.databinding.FragmentStartBinding
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.presentation.ui.adapter.AdapterClick
import com.tauan.desafiostone.presentation.ui.adapter.ItemAdapter
import com.tauan.desafiostone.presentation.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartFragment : Fragment(), AdapterClick {

    private val viewModel: ProductsViewModel by viewModels()
    lateinit var binding: FragmentStartBinding
    private lateinit var adapterList: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.appbar)
        adapterList = ItemAdapter(this)
        setButtonCartClick()
        getItemList()
    }

    private fun setButtonCartClick() {
        binding.btnCart.setOnClickListener { view ->
            val action = StartFragmentDirections.actionStartFragmentToCartFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun getItemList() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.listState.observe(viewLifecycleOwner) { state ->

                if (state.error.isNotBlank()) {
                    binding.imgConnectionError.visibility = View.VISIBLE
                    Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                }
                if (!state.isLoading) {
                    adapterList.products = state.products
                    binding.pgbar.visibility = View.GONE
                    setRecyclerView()
                    observeCartCount()
                }
            }
        }
    }

    private fun observeCartCount() {
        viewModel.itemCountState.observe(viewLifecycleOwner) {
            if (it == null) {
                hideCartButton()
            } else if (it > 0) {
                binding.itemsCount.text = it.toString()
                showCartButton()
            } else {
                hideCartButton()
            }
        }
    }

    private fun showCartButton() {
        binding.itemsCount.visibility = View.VISIBLE
        binding.btnCart.visibility = View.VISIBLE
    }

    private fun hideCartButton() {
        binding.itemsCount.visibility = View.GONE
        binding.btnCart.visibility = View.GONE
    }

    private fun setRecyclerView() {
        with(binding.recycler) {
            val layoutManager = GridLayoutManager(requireContext(), GridLayoutManager.VERTICAL)
            layoutManager.spanCount = 2
            this.layoutManager = layoutManager
            adapter = adapterList
            setHasFixedSize(true)
            visibility = View.VISIBLE
        }
    }

    override fun add(product: Product) {
        val isAdded = viewModel.productState.value?.isAdded ?: false
        lifecycleScope.launch(Dispatchers.IO) {
            if (!isAdded) {
                viewModel.addProductToCart(product)
            } else {
                viewModel.updateProductToCart(product)
            }
        }
    }

    override fun remove(product: Product) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (product.quantity >= 1) {
                viewModel.updateProductToCart(product)
            } else {
                viewModel.removeProductToCart(product)
            }
        }
    }

}