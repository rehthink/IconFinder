package com.rehthink.iconfinder.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import com.rehthink.iconfinder.factory.ViewModelProviderFactory
import com.rehthink.iconfinder.utils.showSnack

abstract class BaseFragment<VM: BaseViewModel,B : ViewBinding> : DaggerFragment() {

    lateinit var binding: B
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater,container)
        viewModel = ViewModelProvider(this,getViewModelFactory())[getViewModel()]
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeData()
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getViewModelFactory(): ViewModelProviderFactory

    open fun observeData() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            view?.showSnack(it.errorMessage)
        })
    }
}