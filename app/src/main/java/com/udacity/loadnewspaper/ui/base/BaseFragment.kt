package com.udacity.loadnewspaper.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.loadnewspaper.di.injectVM
import com.udacity.loadnewspaper.ui.viewmodel.BaseViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment <V: BaseViewModel, VMF : ViewModelProvider.Factory> : Fragment() {
    @Inject
    lateinit var viewmodelFactory : VMF
    lateinit var viewModel: V

    protected abstract fun getLayoutRes(): Int

    protected abstract fun getViewModelType(): Class<V>

    protected abstract fun setupViews(view: View)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

        if (::viewmodelFactory.isInitialized){
            viewModel = injectVM(viewmodelFactory, getViewModelType())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = savedInstanceState ?: arguments
        if (bundle != null)
            extractData(bundle)
    }

    protected open fun extractData(bundle: Bundle) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutRes(), container, false)
        initViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun initViewModel(){
        if (::viewModel.isInitialized){
            onObserve()?.invoke(viewModel)
        }
    }

    protected open fun onObserve(): (V.() -> Unit)? {
        return null
    }

    protected fun <T> doObserve(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }

    fun getAppContext(): Context? {
        return context?.applicationContext
    }
}