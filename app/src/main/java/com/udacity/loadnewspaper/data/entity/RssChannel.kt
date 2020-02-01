package com.udacity.loadnewspaper.data.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "channel", strict = false)
data class RssChannel constructor(
    @field:Element var title: String? = null,
    @field:ElementList(inline = true, required = false) var item: List<RssItem>? = null
)