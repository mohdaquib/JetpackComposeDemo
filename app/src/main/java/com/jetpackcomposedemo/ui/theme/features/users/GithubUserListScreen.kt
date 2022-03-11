package com.jetpackcomposedemo.ui.theme.features.users

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.jetpackcomposedemo.model.response.GithubUser

@ExperimentalCoilApi
@Composable
fun GithubUserListScreen(
    state: GithubUsersContract.State
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            GithubUsersAppBar()
        }
    ) {
        Box {
            GithubUsersList(githubUsers = state.users)
            if (state.isLoading)
                LoadingBar()
        }
    }
}

@Composable
private fun GithubUsersAppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Action Icon",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = { Text(text = stringResource(id = com.jetpackcomposedemo.R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun GithubUsersList(
    githubUsers: List<GithubUser>
) {
    LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
        items(githubUsers) {
            GithubUserItemRow(githubUser = it)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun GithubUserItemRow(
    githubUser: GithubUser,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(5.dp)
            ) {
                GithubUserItemThumbnail(
                    thumbnailUrl = githubUser.avatar_url,
                    iconTransformationBuilder = iconTransformationBuilder
                )
            }
            GithubUserItemDetails(
                item = githubUser, modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun GithubUserItemThumbnail(
    thumbnailUrl: String,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit
) {
    Image(
        painter = rememberImagePainter(
            data = thumbnailUrl,
            builder = iconTransformationBuilder
        ),
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .border(2.dp, androidx.compose.ui.graphics.Color.Transparent, CircleShape),
        contentDescription = "Github User thumbnail picture",
    )
}

@Composable
fun GithubUserItemDetails(
    item: GithubUser?,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = item?.login ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}