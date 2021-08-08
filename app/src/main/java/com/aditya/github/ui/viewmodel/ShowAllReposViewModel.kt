package com.aditya.github.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.aditya.github.model.RepoItem
import com.aditya.github.service.repository.RepoRepository

class ShowAllReposViewModel : BaseViewModel() {
    val showAllReposLiveData = MutableLiveData<List<RepoItem>>()

    fun fetchRepoList(userName:String?) {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList(userName) { isSuccess, response , exception ->
            dataLoading.value = false
            if (isSuccess && response?.isNotEmpty() == true) {
                showAllReposLiveData.value = response
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