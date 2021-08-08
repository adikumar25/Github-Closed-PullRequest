package com.aditya.github.model

data class Base(
    val label: String,
    val ref: String,
    val sha: String,
    val user: User,
    val repo: RepoItem
)
