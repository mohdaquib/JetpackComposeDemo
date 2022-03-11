package com.jetpackcomposedemo.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcomposedemo.ui.theme.features.users.GithubUserListScreen
import com.jetpackcomposedemo.ui.theme.features.users.GithubUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                JetpackComposeApp()
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun JetpackComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationKeys.Route.GITHUB_USER_LIST
    ) {
        composable(route = NavigationKeys.Route.GITHUB_USER_LIST) {
            GithubUsersListDestination()
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun GithubUsersListDestination() {
    val viewModel: GithubUsersViewModel = hiltViewModel()
    GithubUserListScreen(
        state = viewModel.state
    )
}

object NavigationKeys {
    object Route {
        const val GITHUB_USER_LIST = "github_user_list"
    }
}