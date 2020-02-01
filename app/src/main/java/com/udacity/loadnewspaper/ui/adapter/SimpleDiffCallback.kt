package com.udacity.loadnewspaper.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.udacity.loadnewspaper.data.entity.RssItem

class SimpleDiffCallback(var oldList: List<RssItem>, var newList: List<RssItem>) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].link!!.equals(newList[newItemPosition].link!!, false)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (newList[newItemPosition].link.equals(oldList[oldItemPosition].link))
                && (newList[newItemPosition].description.equals(oldList[oldItemPosition].description))
                && (newList[newItemPosition].pubDate.equals(oldList[oldItemPosition].pubDate))
    }

}