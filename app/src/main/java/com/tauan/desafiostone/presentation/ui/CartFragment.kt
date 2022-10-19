package com.tauan.desafiostone.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tauan.desafiostone.databinding.FragmentCartBinding
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.presentation.ui.adapter.AdapterClick
import com.tauan.desafiostone.presentation.ui.adapter.ItemAdapter
import com.tauan.desafiostone.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(), AdapterClick {

    lateinit var viewModel: CartViewModel

    private lateinit var adapterList: ItemAdapter
    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAppBar()

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        adapterList = ItemAdapter(this)
        observeCartList()
        observeCartValue()
    }

    private fun setAppBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.appbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.setNavigationOnClickListener { view ->
            val action = CartFragmentDirections.actionCartFragmentToStartFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun observeCartList() {
        lifecycleScope.launch {
            viewModel.allItemsFromCart.observe(viewLifecycleOwner) { state ->
                if (state.error.isNotBlank()) {
                    Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                }
                if (!state.isLoading) {
                    adapterList.products = state.products as MutableList<Product>
                    binding.pgbar.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE
                    setRecyclerView()
                }
            }
        }
    }

    private fun observeCartValue() {
        lifecycleScope.launch {
            viewModel.totalValueLiveData.observe(viewLifecycleOwner) { totalValue ->
                binding.txtTotal.text = "$totalValue G"
            }
        }
    }

    private fun setRecyclerView() {
        with(binding.carList) {
            val layoutManager = GridLayoutManager(requireContext(), GridLayoutManager.VERTICAL)
            layoutManager.spanCount = 2
            this.layoutManager = layoutManager
            adapter = adapterList
            setHasFixedSize(true)
            visibility = View.VISIBLE
        }
    }

    override fun add(product: Product) {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.updateProductToCart(product)

        }
    }

    override fun remove(product: Product) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (product.quantity >= 1) {
                viewModel.updateProductToCart(product)
            } else {
                viewModel.deleteProductToCart(product)
                val position = adapterList.products.indexOf(product)
                adapterList.removeItem(position)
            }
        }
    }
}