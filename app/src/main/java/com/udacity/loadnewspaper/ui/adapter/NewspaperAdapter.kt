package com.udacity.loadnewspaper.ui.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacity.loadnewspaper.R
import com.udacity.loadnewspaper.data.entity.RssItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_newspaper.view.*

class NewspaperAdapter(
    private val context: Context,
    private val listItem: ArrayList<RssItem>,
    private val onClickListener: (item : RssItem) -> Unit
) : RecyclerView.Adapter<NewspaperAdapter.NewspaperHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewspaperHolder {
        val layoutInflater = LayoutInflater.from(context)
        return NewspaperHolder(layoutInflater.inflate(R.layout.item_newspaper, parent, false))
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: NewspaperHolder, position: Int) {
        holder.bindView(listItem[position])
        holder.itemView.setOnClickListener { onClickListener(listItem[position]) }
    }

    fun updateList(newList: List<RssItem>?) {
        if (newList != null) {
            var diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(listItem, newList))
            listItem.clear()
            listItem.addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    class NewspaperHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bindView(item: RssItem) {
            itemView.tv_title.text = item.title
            itemView.tv_info.text = item.description
            itemView.tv_date.text = item.pubDate
            Glide.with(itemView.iv_image.context).load(item.imgSource).into(itemView.iv_image)
        }

    }

}