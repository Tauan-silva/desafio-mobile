package com.tauan.desafiostone.presenter.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tauan.desafiostone.BR
import com.tauan.desafiostone.R
import com.tauan.desafiostone.databinding.ItemCardLayoutBinding
import com.tauan.desafiostone.model.Item
import com.tauan.desafiostone.presenter.viewmodel.CartViewModel

class ItemViewHolder(itemView: ItemCardLayoutBinding, private val cartViewModel: CartViewModel, private val adapterClick: AdapterClick) : RecyclerView.ViewHolder(itemView.root) {
    private val binding = itemView

    fun bind(item: Item) {

        with(binding) {
            viewmodel = cartViewModel
            setVariable(BR.item, item)

            Picasso.get()
                .load(item.thumbnailHd)
                .error(R.drawable.ic_image_error_foreground)
                .into(image)

            btnAdd.setOnClickListener {
                adapterClick.add(item)
                btnAdd.visibility = View.GONE
                elegantNumber.visibility = View.VISIBLE
            }

            btnAddMore.setOnClickListener {
                adapterClick.add(item)
            }

            btnRemove.setOnClickListener {
                adapterClick.remove(item)
                if (item.quantity == 0){
                    btnAdd.visibility = View.VISIBLE
                    elegantNumber.visibility = View.GONE
                }
            }
        }

    }
}