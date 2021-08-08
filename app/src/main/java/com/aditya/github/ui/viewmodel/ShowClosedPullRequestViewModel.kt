package com.aditya.github.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.aditya.github.model.PullRequestItem
import com.aditya.github.service.repository.PullRequestRepository

class ShowClosedPullRequestViewModel : BaseViewModel() {
    val showClosedPullRequestLiveData = MutableLiveData<List<PullRequestItem>>()

    fun fetchClosedPullRequest(userName: String?, repoName: String?) {
        dataLoading.value = true
        PullRequestRepository.getInstance().getClosedPullRequests(userName, repoName) { isSuccess, response, exception->
            dataLoading.value = false
            if (isSuccess && response?.isNotEmpty() == true) {
                showClosedPullRequestLiveData.value = response
                empty.value = false
            } else {
                empty.value = true
                if (exception?.message?.isBlank() == false){
                    toastMessage.value = exception.message
                }
            }
        }
    }
}