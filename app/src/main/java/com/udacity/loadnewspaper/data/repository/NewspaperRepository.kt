package com.udacity.loadnewspaper.data.repository

import com.udacity.loadnewspaper.data.api.RetrofitNewspaperApi
import com.udacity.loadnewspaper.data.entity.RssFeed
import com.udacity.loadnewspaper.data.entity.RssItem

class NewspaperRepository(private val newspaperApi: RetrofitNewspaperApi) {

    fun getRssFeed() : RssFeed{
        return newspaperApi.getRssFeed()
    }

    fun getListRssItem(rssFeed: RssFeed) : List<RssItem>{
        val result = mutableListOf<RssItem>()
        for (item in rssFeed.channel!!.item!!){
            var pos = item.description!!.indexOf("</br>")
            if (pos != -1){
                var startImg = item.description!!.indexOf("src=\"")
                if (startImg != -1){
                    var endImg = item.description!!.indexOf("\"", startImg + 5)
                    item.imgSource = item.description!!.substring(startImg + 5, endImg)
                }
                item.description = item.description!!.substring(pos + 5)
            }
            result.add(item)
        }
        return result
    }

}