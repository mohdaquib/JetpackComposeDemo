package com.jetpackcomposedemo.model.data

import com.jetpackcomposedemo.model.response.GithubUser
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRemoteSource @Inject constructor(private val githubUsersApi: GithubUsersApi){

    suspend fun getGithubUsers(): List<GithubUser>? = withContext(Dispatchers.IO) {
        return@withContext githubUsersApi.getGithubUsers(150).mapResponseToGithubUsers()
    }

    private fun ResponseBody.mapResponseToGithubUsers(): List<GithubUser>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java, GithubUser::class.java)
        val adapter: JsonAdapter<List<GithubUser>> = moshi.adapter(listType)
        return adapter.fromJson(string())
    }
}