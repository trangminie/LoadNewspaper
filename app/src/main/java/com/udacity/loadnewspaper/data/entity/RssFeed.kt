package com.udacity.loadnewspaper.data.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssFeed constructor(
    @field:Element var channel: RssChannel? = null
)