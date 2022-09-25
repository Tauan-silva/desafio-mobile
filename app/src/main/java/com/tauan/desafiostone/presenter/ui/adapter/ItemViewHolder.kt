package com.tauan.desafiostone.presenter.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tauan.desafiostone.R
import com.tauan.desafiostone.databinding.ItemCardLayoutBinding
import com.tauan.desafiostone.model.Item

class ItemViewHolder(itemView: ItemCardLayoutBinding, private val adapterClick: AdapterClick) : RecyclerView.ViewHolder(itemView.root) {
    private val binding = itemView

    fun bind(item: Item) {

        with(binding) {
            Picasso.get()
                .load(item.thumbnailHd)
                .error(R.drawable.ic_image_error_foreground)
                .into(image)

            itemName.text = item.title
            itemPrice.text = "G ${item.price}"
            seller.text = item.seller

            btnAdd.setOnClickListener {
                adapterClick.add(item)
                labelCount.text = item.quantity.toString()
                btnAdd.visibility = View.GONE
                elegantNumber.visibility = View.VISIBLE
            }

            btnAddMore.setOnClickListener {
                adapterClick.add(item)
                labelCount.text = item.quantity.toString()
            }

            btnRemove.setOnClickListener {
                adapterClick.remove(item)
                labelCount.text = item.quantity.toString()
            }
        }

    }
}