package com.udacity.loadnewspaper.data.api

import com.udacity.loadnewspaper.data.entity.RssFeed
import com.udacity.loadnewspaper.data.entity.RssItem

interface ApiService {
    fun getRssFeed(): RssFeed
}