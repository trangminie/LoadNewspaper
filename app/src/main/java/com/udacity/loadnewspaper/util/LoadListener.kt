package com.udacity.loadnewspaper.util

import android.util.Log
import android.webkit.JavascriptInterface

class LoadListener {
    @SuppressWarnings("unused")
    @JavascriptInterface
    fun processHTML(html: String) {
    }
}