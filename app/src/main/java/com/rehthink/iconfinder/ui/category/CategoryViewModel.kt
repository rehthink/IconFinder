package com.rehthink.iconfinder.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import com.rehthink.iconfinder.base.BaseViewModel
import com.rehthink.iconfinder.model.Category
import com.rehthink.iconfinder.network.Resource
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    BaseViewModel() {

    private var count = 20
    private val _categoryData = MutableLiveData<List<Category>>()
    private var totalCount = 0

    fun loadCategory(initialCount: Int = -1) = viewModelScope.launch {
        if(initialCount != -1)
            count = initialCount

        notifyLoading(true)
        val resource = categoryRepository.getCategory(count)
        notifyLoading(false)

        when(resource) {
            is Resource.Success -> {
                totalCount = resource.result.totalCount

                if (count < resource.result.totalCount) {
                    count += count
                }

                _categoryData.value = resource.result.categories
            }

            is Resource.Error -> {
                resource.notifyError()
            }
        }
    }

    val categoryData: LiveData<List<Category>>
        get() = _categoryData

    val nextCount: Int get() = count

    val hasMoreData: Boolean get() = count < totalCount

}