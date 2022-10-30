package com.rehthink.iconfinder.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rehthink.iconfinder.network.Resource

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Resource.Error>()

    val loading: LiveData<Boolean>
        get() = _loading

    val error: LiveData<Resource.Error>
        get() = _error

    fun notifyLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }

    fun Resource.Error.notifyError() {
        _error.value = this
    }

}