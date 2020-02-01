package com.udacity.loadnewspaper.ui.fragment

import android.os.Bundle
import android.view.View
import com.udacity.loadnewspaper.R
import com.udacity.loadnewspaper.di.module.ViewModelFactory
import com.udacity.loadnewspaper.ui.base.BaseFragment
import com.udacity.loadnewspaper.ui.viewmodel.DetailFragmentVM
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : BaseFragment<DetailFragmentVM, ViewModelFactory>() {
    var url : String = ""

    override fun getLayoutRes(): Int = R.layout.detail_fragment

    override fun getViewModelType(): Class<DetailFragmentVM> = DetailFragmentVM::class.java

    override fun setupViews(view: View) {
        webview.loadUrl(url)
    }

    override fun extractData(bundle: Bundle) {
        url = bundle.getString(URL_LINK, "google.com.vn")
    }

    companion object{
        const val URL_LINK = "url_link"
        fun newInstance(url : String) : DetailFragment{
            val args = Bundle()
            args.putString(URL_LINK, url)
            val  fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

}