package com.udacity.loadnewspaper.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.udacity.loadnewspaper.ui.viewmodel.BaseViewModel

fun <T:BaseViewModel> FragmentActivity.injectVM(factory : ViewModelProvider.Factory, clazz: Class<T>) : T{
    return ViewModelProviders.of(this, factory).get(clazz)
}

fun <T:BaseViewModel> Fragment.injectVM(factory : ViewModelProvider.Factory, clazz: Class<T>) : T{
    return ViewModelProviders.of(this, factory).get(clazz)
}