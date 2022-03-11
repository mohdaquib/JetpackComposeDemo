package com.jetpackcomposedemo.model.data

import com.jetpackcomposedemo.model.response.GithubUserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUsersApi @Inject constructor(private val service: Service){
    suspend fun getGithubUsers(since: Int): ResponseBody = service.getGithubUsers(since)

    interface Service {
        @GET("users")
        suspend fun getGithubUsers(@Query("since") since: Int): ResponseBody
    }

    companion object {
        const val API_URL = "https://api.github.com/"
    }
}