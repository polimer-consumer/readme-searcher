package com.polimerconsumer.springgitapi.model

data class Repository(
    val name: String,
    val owner: Owner,
    val htmlUrl: String?,
    var isHighlighted: Boolean = false
)