package com.rehthink.iconfinder.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<Data>(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    abstract fun bindTo(data: Data)
}