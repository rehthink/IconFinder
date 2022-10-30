package com.rehthink.iconfinder.ui.iconset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rehthink.iconfinder.base.BaseViewHolder
import com.rehthink.iconfinder.databinding.ItemIconSetBinding
import com.rehthink.iconfinder.model.IconSet

class IconSetAdapter(private val click: (IconSet) -> Unit): ListAdapter<IconSet, IconSetAdapter.IconSetViewHolder>(
    diffUtil
) {


    inner class IconSetViewHolder(private val binding: ItemIconSetBinding): BaseViewHolder<IconSet>(binding) {

        override fun bindTo(data: IconSet) {
            binding.tvIconSetName.text = data.name
            binding.tvCount.text = "${data.iconsCount}"
            binding.root.setOnClickListener {
                click.invoke(data)
            }
        }
    }

    companion object {

        val diffUtil = object :DiffUtil.ItemCallback<IconSet>() {
            override fun areItemsTheSame(oldItem: IconSet, newItem: IconSet): Boolean
               = oldItem.iconSetId == newItem.iconSetId

            override fun areContentsTheSame(oldItem: IconSet, newItem: IconSet): Boolean = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconSetViewHolder =
        IconSetViewHolder(ItemIconSetBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: IconSetViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}