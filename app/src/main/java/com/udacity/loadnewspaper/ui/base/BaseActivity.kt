package com.udacity.loadnewspaper.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.loadnewspaper.data.local.PreferenceHelper
import com.udacity.loadnewspaper.di.injectVM
import com.udacity.loadnewspaper.ui.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity <V : BaseViewModel, VMF : ViewModelProvider.Factory> : DaggerAppCompatActivity(){
    private val TAG = BaseActivity::class.java.simpleName
    @Inject
    lateinit var preferenceHelper: PreferenceHelper
    @Inject
    lateinit var viewModelFactory : VMF
    lateinit var viewModel : V

    protected abstract fun getViewModelType() : Class<V>?

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        extractData(intent)
        initViewModel()
    }

    private fun initViewModel(){
        getViewModelType() ?: return

        if (::viewModelFactory.isInitialized)
            viewModel = injectVM(viewModelFactory, getViewModelType()!!)

        if (::viewModel.isInitialized)
            onObserve()?.invoke(viewModel)

    }

    protected open fun onObserve(): (V.() -> Unit)? {
        return null
    }

    protected fun <T> doObserve(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(this, observer)
    }

    private fun extractData(intent: Intent?) {
        intent ?: return
    }

}