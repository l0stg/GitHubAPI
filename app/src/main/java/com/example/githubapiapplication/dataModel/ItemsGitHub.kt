package com.example.githubapiapplication

import java.io.Serializable

data class ItemsGitHub(
    val id: Int?,
    val name: String,
    val description: String,
    val html_url: String,
    val owner: Owner
) : Serializable

data class Owner(
    val login: String,
    val avatar_url: String
)
