package com.aditya.github.service.apiClient

import com.aditya.github.model.PullRequestItem
import com.aditya.github.model.RepoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{user_name}/repos")
    fun getRepos(@Path("user_name") userName : String): Call<List<RepoItem>>

    @GET("repos/{user_name}/{repo_name}/pulls")
    fun getClosedPullRequests(@Path("user_name") userName : String,
                              @Path("repo_name") repoName : String,
                              @Query("state") state:String = "closed"): Call<List<PullRequestItem>>
}