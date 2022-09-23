package com.tauan.desafiostone.ui.adapter

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
            btnAdd.setOnClickListener { adapterClick.onClick(item) }
        }

    }
}