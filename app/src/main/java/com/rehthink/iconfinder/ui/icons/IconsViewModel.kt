package com.rehthink.iconfinder.ui.icons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.rehthink.iconfinder.base.BaseViewModel
import com.rehthink.iconfinder.model.Icon
import com.rehthink.iconfinder.network.Resource
import javax.inject.Inject

class IconsViewModel @Inject constructor(private val iconRepository: IconRepository) :
    BaseViewModel() {

    private var count = 10
    private val _icons = MutableLiveData<List<Icon>>()
    private var totalCount = 0

    fun loadIcons(iconSetId: Int, initialCount: Int = -1) = viewModelScope.launch {
        if (initialCount != -1)
            count = initialCount

        notifyLoading(true)
        val resource = iconRepository.loadIcons(iconSetId, count)
        notifyLoading(false)

        when (resource) {
            is Resource.Success -> {
                totalCount = resource.result.totalCount

                if (count < resource.result.totalCount) {
                    count += count
                }

                _icons.value = resource.result.icons
            }

            is Resource.Error -> {
                resource.notifyError()
            }
        }
    }

    val iconsData: LiveData<List<Icon>>
        get() = _icons

    val nextCount: Int get() = count

    val hasMoreData: Boolean get() = count < totalCount
}