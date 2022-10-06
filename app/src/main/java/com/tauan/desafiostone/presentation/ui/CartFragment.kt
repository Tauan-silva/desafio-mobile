package com.tauan.desafiostone.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.tauan.desafiostone.databinding.FragmentCartBinding
import com.tauan.desafiostone.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    val viewModel: CartViewModel by viewModels()
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
    }

    private fun setAppBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.appbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.setNavigationOnClickListener { view ->
            val action = CartFragmentDirections.actionCartFragmentToStartFragment()
            view.findNavController().navigate(action)
        }
    }
}