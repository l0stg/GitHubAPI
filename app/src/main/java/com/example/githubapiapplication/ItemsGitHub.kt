package com.example.githubapiapplication

import java.io.Serializable

data class ItemsGitHub(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val html_url: String? = null,
    val owner: Owner? = Owner()
) : Serializable

data class Owner(
    val login: String? = null,
    val avatar_url: String? = null
)
