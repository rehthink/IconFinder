package com.rehthink.iconfinder.ui.iconset

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.rehthink.iconfinder.base.BaseViewModel
import com.rehthink.iconfinder.model.IconSet
import com.rehthink.iconfinder.network.Resource
import javax.inject.Inject

class IconSetViewModel @Inject constructor(private val iconSetRepository: IconSetRepository): BaseViewModel() {

    private var count = 20
    private val _iconSet = MutableLiveData<List<IconSet>>()
    private var totalCount = 0

    fun loadIconSet( category: String, premium:Boolean = false, initialCount:Int = -1) = viewModelScope.launch {
        if(initialCount != -1) count = initialCount

        notifyLoading(true)
        val resource = iconSetRepository.loadIconSet(category,count,premium)
        notifyLoading(false)

        when(resource) {
            is Resource.Success -> {
                totalCount = resource.result.totalCount

                if (count < resource.result.totalCount) {
                    count += count
                }

                _iconSet.value = resource.result.iconSets
            }

            is Resource.Error -> {
                resource.notifyError()
            }
        }
    }

    val iconSetData: LiveData<List<IconSet>>
        get() = _iconSet

    val nextCount: Int get() = count

    val hasMoreData: Boolean get() = count < totalCount
}