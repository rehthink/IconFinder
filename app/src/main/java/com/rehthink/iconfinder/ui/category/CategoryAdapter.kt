package com.rehthink.iconfinder.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rehthink.iconfinder.base.BaseViewHolder
import com.rehthink.iconfinder.databinding.ItemCategoryBinding
import com.rehthink.iconfinder.model.Category

class CategoryAdapter(private val onClick: (Category) -> Unit): ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(
    diffUtil
) {


    inner class CategoryViewHolder(private val binding: ItemCategoryBinding): BaseViewHolder<Category>(binding) {

        override fun bindTo(data: Category) {
            binding.tvCategory.text = data.name
            binding.tvCategory.setOnClickListener {
                onClick.invoke(data)
            }
        }
    }

    companion object {

        val diffUtil = object :DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean
               = oldItem.identifier == newItem.identifier

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}