package com.aditya.github.service.repository

import com.aditya.github.model.RepoItem
import com.aditya.github.service.apiClient.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    // GET repo list
    fun getRepoList(userName:String?, onResult: (isSuccess: Boolean, response: List<RepoItem>?, exception: Exception?) -> Unit) {

        if (userName.isNullOrBlank()) {
            onResult(false, null, Exception("Invalid Username"))
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            HttpClient.instance.getRepos(userName).enqueue(object : Callback<List<RepoItem>> {
                override fun onResponse(call: Call<List<RepoItem>>?, response: Response<List<RepoItem>>?) {
                    if (response != null && response.isSuccessful)
                        onResult(true, response.body(), null)
                    else
                        onResult(false, null, Exception(response?.message()))
                }
                override fun onFailure(call: Call<List<RepoItem>>?, t: Throwable?) {
                    onResult(false, null, Exception(t?.message))
                }

            })
        }
    }

    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE
            ?: RepoRepository().also {
                INSTANCE = it
            }
    }
}