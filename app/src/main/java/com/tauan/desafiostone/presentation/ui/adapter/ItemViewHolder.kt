package com.tauan.desafiostone.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tauan.desafiostone.BR
import com.tauan.desafiostone.R
import com.tauan.desafiostone.databinding.ItemCardLayoutBinding
import com.tauan.desafiostone.domain.model.Product

class ItemViewHolder(itemView: ItemCardLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
    val binding = itemView

    fun bind(product: Product) {

        with(binding) {
            setVariable(BR.item, product)

            Picasso.get()
                .load(product.thumbnailHd)
                .error(R.drawable.ic_image_error_foreground)
                .into(image)

        }

    }
}