package com.udacity.loadnewspaper.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.loadnewspaper.LoadNewspaperApp
import com.udacity.loadnewspaper.data.repository.NewspaperRepository
import com.udacity.loadnewspaper.ui.viewmodel.DetailFragmentVM
import com.udacity.loadnewspaper.ui.viewmodel.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    application : LoadNewspaperApp,
    newspaperRepository: NewspaperRepository
) : ViewModelProvider.Factory{

    private val genVMMap: Map<Class<*>, (() -> Any)> = mapOf(
        MainViewModel::class.java to {MainViewModel(application, newspaperRepository)},
        DetailFragmentVM::class.java to {DetailFragmentVM(application)}
    )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return genVMMap.getValue(modelClass).invoke() as T
    }

}