package com.udacity.loadnewspaper.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.udacity.loadnewspaper.R
import com.udacity.loadnewspaper.di.module.ViewModelFactory
import com.udacity.loadnewspaper.ui.base.BaseActivity
import com.udacity.loadnewspaper.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailActivity : BaseActivity<MainViewModel, ViewModelFactory>()  {
    override fun getViewModelType(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_fragment)
        if (preferenceHelper.getNightMode()) {
            webview.settings.javaScriptEnabled = true
            webview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    webview.loadUrl(
                        "javascript:(function() {" +
                                "document.body.style.setProperty(\"background\", \"black\");" +
                                "document.body.style.setProperty(\"color\", \"white\");" +
                                "})()"
                    )
                    webview.visibility = View.VISIBLE
                    progress_circular.visibility = View.GONE
                }
            }
//            webview.addJavascriptInterface(LoadListener(), "HTMLOUT")
//            webview.webViewClient = object : WebViewClient() {
//                override fun shouldOverrideUrlLoading(
//                    view: WebView?,
//                    request: WebResourceRequest?
//                ): Boolean {
//                    return true
//                }
//
//                override fun onPageFinished(view: WebView, url: String) {
//                    view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');")
//                }
//            }
        } else {
            webview.visibility = View.VISIBLE
            progress_circular.visibility = View.GONE
        }
        webview.loadUrl(intent.getStringExtra(URL_LINK))

    }

    companion object{
        private const val URL_LINK = "url_link"
        fun getIntent(context: Context?, url : String) : Intent{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(URL_LINK, url)
            return intent
        }
    }
}
