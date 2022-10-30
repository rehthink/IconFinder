package com.rehthink.iconfinder.ui.icons

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rehthink.iconfinder.base.BaseViewHolder
import com.rehthink.iconfinder.databinding.ItemIconBinding
import com.rehthink.iconfinder.model.Icon
import com.rehthink.iconfinder.utils.loadImage

class IconAdapter(private val click: (Icon, ImageView) -> Unit): ListAdapter<Icon, IconAdapter.IconViewHolder>(
    diffUtil
) {


    inner class IconViewHolder(private val binding: ItemIconBinding): BaseViewHolder<Icon>(binding) {

        override fun bindTo(data: Icon) {
            data.raster_sizes[data.raster_sizes.size - 1].apply {
                binding.ivIcon.loadImage(formats[0].preview_url)
            }

            binding.root.setOnClickListener {
                click.invoke(data, binding.ivIcon)
            }
        }
    }

    companion object {

        val diffUtil = object :DiffUtil.ItemCallback<Icon>() {
            override fun areItemsTheSame(oldItem: Icon, newItem: Icon): Boolean
               = oldItem.icon_id == newItem.icon_id

            override fun areContentsTheSame(oldItem: Icon, newItem: Icon): Boolean = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder =
        IconViewHolder(ItemIconBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}