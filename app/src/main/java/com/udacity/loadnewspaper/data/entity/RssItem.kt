package com.udacity.loadnewspaper.data.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class RssItem constructor(
    @field:Element var title: String? = null,
    @field:Element var link: String? = null,
    @field:Element var pubDate: String? = null,
    @field:Element var description: String? = null,
    var imgSource: String? = null
)