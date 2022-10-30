package com.rehthink.iconfinder.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.rehthink.iconfinder.base.BaseViewModel
import com.rehthink.iconfinder.model.Icon
import com.rehthink.iconfinder.network.Resource
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository): BaseViewModel() {

    private var count = 20
    private val _icons = MutableLiveData<List<Icon>>()
    private var totalCount = 0
    private var cleared = false

    fun clearSearch() {
        cleared = true
        _icons.value = emptyList()
    }

    fun searchIcon(query: String, initialCount: Int = -1) = viewModelScope.launch {
        if(initialCount != -1) count = initialCount

        notifyLoading(true)
        val resource = searchRepository.search(query, count)
        notifyLoading(false)

        when (resource) {
            is Resource.Success -> {
                totalCount = resource.result.totalCount

                if (count < resource.result.totalCount) {
                    count += count
                }

                cleared = false
                _icons.value = resource.result.icons
            }

            is Resource.Error -> {
                resource.notifyError()
            }
        }
    }


    val iconsData: LiveData<List<Icon>>
        get() = _icons

    val hasMoreData:Boolean get() = count < totalCount

    val isCleared : Boolean get() = cleared
}