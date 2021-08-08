package com.aditya.github.service.repository

import com.aditya.github.model.PullRequestItem
import com.aditya.github.service.apiClient.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestRepository {

    // GET closed pull request list
    fun getClosedPullRequests(userName:String?, repoName:String?, onResult: (isSuccess: Boolean, response: List<PullRequestItem>?, exception: Exception?) -> Unit) {

        if (userName.isNullOrBlank()) {
            onResult(false, null, Exception("Invalid Username"))
            return
        }

        if (repoName.isNullOrBlank()) {
            onResult(false, null, Exception("Invalid Reponame"))
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            HttpClient.instance.getClosedPullRequests(userName, repoName).enqueue(object : Callback<List<PullRequestItem>> {
                override fun onResponse(call: Call<List<PullRequestItem>>?, response: Response<List<PullRequestItem>>?) {
                    if (response != null && response.isSuccessful)
                        onResult(true, response.body(), null)
                    else
                        onResult(false, null, Exception(response?.message()))
                }
                override fun onFailure(call: Call<List<PullRequestItem>>?, t: Throwable?) {
                    onResult(false, null, Exception(t?.message))
                }

            })
        }
    }

    companion object {
        private var INSTANCE: PullRequestRepository? = null
        fun getInstance() = INSTANCE
            ?: PullRequestRepository().also {
                INSTANCE = it
            }
    }
}