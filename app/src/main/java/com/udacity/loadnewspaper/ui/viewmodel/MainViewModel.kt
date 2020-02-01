package com.udacity.loadnewspaper.ui.viewmodel

import android.util.Log
import android.util.Xml
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.loadnewspaper.LoadNewspaperApp
import com.udacity.loadnewspaper.data.entity.RssItem
import com.udacity.loadnewspaper.data.repository.NewspaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel constructor(
    application: LoadNewspaperApp,
    private val newspaperRepository: NewspaperRepository
) : BaseViewModel(application) {

    internal var newspaperListLD = MutableLiveData<List<RssItem>>()

    fun getRssFeed() {
        viewModelScope.launch(Dispatchers.Main) {
            val rssFeed = withContext(Dispatchers.Default) {
                newspaperRepository.getRssFeed()
            }
            newspaperListLD.postValue(newspaperRepository.getListRssItem(rssFeed))
        }
    }
}