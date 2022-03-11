package com.jetpackcomposedemo.ui.theme.features.users

import com.jetpackcomposedemo.model.response.GithubUser

class GithubUsersContract {

    data class State(
        val users: List<GithubUser> = listOf(),
        val isLoading: Boolean = false
    )
}