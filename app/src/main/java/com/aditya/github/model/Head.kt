package com.aditya.github.model

data class Head(
    val label: String,
    val ref: String,
    val sha: String,
    val user: User
)