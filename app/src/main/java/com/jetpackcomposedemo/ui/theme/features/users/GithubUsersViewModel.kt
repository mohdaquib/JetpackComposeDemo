package com.jetpackcomposedemo.ui.theme.features.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcomposedemo.model.data.GithubUserRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUsersViewModel @Inject constructor(private val githubUserRemoteSource: GithubUserRemoteSource) :
    ViewModel() {
    var state by mutableStateOf(
        GithubUsersContract.State(users = listOf(), isLoading = true)
    )
        private set

    init {
        viewModelScope.launch { getGithubUsers() }
    }

    private suspend fun getGithubUsers() {
        val users = githubUserRemoteSource.getGithubUsers()
        viewModelScope.launch {
            state = state.copy(users = users!!, isLoading = false)
        }
    }
}