package com.aditya.github.model

data class Links(
    val self: Href,
    val html: Href,
    val issue: Href,
    val comments: Href,
    val review_comments: Href,
    val review_comment: Href,
    val commits: Href,
    val statuses: Href
)
