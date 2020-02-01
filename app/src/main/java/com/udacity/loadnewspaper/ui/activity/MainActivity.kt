package com.udacity.loadnewspaper.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.loadnewspaper.R
import com.udacity.loadnewspaper.data.entity.RssItem
import com.udacity.loadnewspaper.di.module.ViewModelFactory
import com.udacity.loadnewspaper.ui.adapter.NewspaperAdapter
import com.udacity.loadnewspaper.ui.base.BaseActivity
import com.udacity.loadnewspaper.ui.fragment.DetailFragment
import com.udacity.loadnewspaper.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ViewModelFactory>() {
    private var isRefresh = false
    private lateinit var newspaperAdapter: NewspaperAdapter
    private var listItems = ArrayList<RssItem>()

    override fun getViewModelType(): Class<MainViewModel>? {
       return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNightMode(true)

        setContentView(R.layout.activity_main)

        swipe_layout.setOnRefreshListener {
            if (!isRefresh){
                isRefresh = true
                viewModel.getRssFeed()
            }
        }

        newspaperAdapter = NewspaperAdapter(this, listItems){
//            val fragment = DetailFragment.newInstance(it.link!!)
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(android.R.id.content, fragment,fragment.javaClass.canonicalName)
//            transaction.addToBackStack(fragment.javaClass.canonicalName)
//            transaction.commit()
            startActivity(DetailActivity.getIntent(this, it.link!!))
        }
        rv_items.adapter = newspaperAdapter
        rv_items.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel.getRssFeed()
    }

    override fun onObserve(): (MainViewModel.() -> Unit)? = {
        doObserve(newspaperListLD, Observer {
            swipe_layout.isRefreshing = false
            isRefresh = false
            newspaperAdapter.updateList(newspaperListLD.value)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_mode -> {
                preferenceHelper.setNightMode(!preferenceHelper.getNightMode())
                setupNightMode(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupNightMode(isOnCreate : Boolean){
        val mode = if (preferenceHelper.getNightMode()) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
        if (!isOnCreate) delegate.setLocalNightMode(mode)
    }

}
