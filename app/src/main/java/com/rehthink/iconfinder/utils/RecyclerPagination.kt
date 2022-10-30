package com.rehthink.iconfinder.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerPagination(
    private val layoutManager: RecyclerView.LayoutManager,
    private val rc: RecyclerView
) :
    RecyclerView.OnScrollListener() {
    private var shouldLoadMore = true

    private var callback: ((RecyclerPagination) -> Unit)? = null

    fun setShouldLoadMore(shouldLoadMore: Boolean) {
        this.shouldLoadMore = shouldLoadMore
    }

    fun onPaginate(callback: (RecyclerPagination) -> Unit) {
        this.callback = callback
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var paginationCondition = false

        when(layoutManager) {
            is LinearLayoutManager -> {
                val visibleItemCount = rc.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                paginationCondition = firstVisibleItem + visibleItemCount == totalItemCount
            }

            is GridLayoutManager -> {
                paginationCondition = layoutManager.findLastCompletelyVisibleItemPosition()+1 == layoutManager.itemCount
            }
        }

        if (shouldLoadMore) {
            if (paginationCondition) {
                callback?.invoke(this)
                shouldLoadMore = false
            }
        }
    }

}